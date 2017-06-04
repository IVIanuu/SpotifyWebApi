package com.ivianuu.spotifyextensions;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ivianuu.spotifywebapi.SpotifyService;
import com.ivianuu.spotifywebapi.model.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * A class which helps with pagination
 */

public final class PaginationHelper<T extends Parcelable> {

    private static final String TAG = PaginationHelper.class.getSimpleName();

    // make sure to not interfere with user's keys
    private static final String KEY_PREFIX = PaginationHelper.class.getSimpleName() + "_";

    private static final String KEY_LIMIT = KEY_PREFIX + "limit";
    private static final String KEY_OFFSET = KEY_PREFIX + "offset";
    private static final String KEY_TOTAL = KEY_PREFIX + "total";
    private static final String KEY_ALL_RECEIVED = KEY_PREFIX + "all_received";
    private static final String KEY_FIRST_PAGE_FETCHED = KEY_PREFIX + "first_page_fetched";
    private static final String KEY_ALL_ITEMS = KEY_PREFIX + "all_items";
    private static final String KEY_LATEST_ITEMS = KEY_PREFIX + "latest_items";

    private HashMap<String, Object> options;

    private int limit;
    private int offset = 0;
    private int total = -1;

    private Fetcher<T> fetcher;
    private Disposable fetchingDisposable;

    private boolean fetching;
    private boolean allReceived;
    private boolean firstPageFetched;

    private List<T> allItems = new ArrayList<>();
    private List<T> latestItems = new ArrayList<>();

    private PaginationHelper(Builder<T> builder) {
        this.options = builder.defaultOptions;
        if (options == null) options = new HashMap<>();
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.fetcher = builder.fetcher;

        // build options
        options.put(SpotifyService.QUERY_PARAMETER.LIMIT, limit);
        options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);
        Log.d(TAG, "init");
    }

    /**
     * Fetches the next page
     */
    public void nextPage() {
        Log.d(TAG, "next page");
        if (allReceived || fetching) {
            Log.d(TAG, "should not fetch next age");
            // load only if not all items are received and we not already fetching a page
            return;
        }

        // fetch
        fetchingDisposable = fetcher.fetch(options)
                .subscribe(new Consumer<Pager<T>>() {
                    @Override
                    public void accept(Pager<T> tPager) throws Exception {
                        Log.d(TAG, "fetch success");
                        // update lists
                        allItems.addAll(tPager.items);
                        allItemsPublisher.onNext(allItems);

                        latestItems.clear();
                        latestItems.addAll(tPager.items);
                        latestItemsPublisher.onNext(latestItems);

                        allReceived = tPager.next == null;
                        if (!firstPageFetched) firstPageFetched = true;

                        fetching = false;

                        // update options
                        offset += limit;
                        options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "fetch error");
                        // notify error
                        errorPublisher.onNext(throwable);
                    }
                });

        fetching = true;
    }

    /**
     * Resets this instance
     */
    public void reset() {
        offset = 0;
        total = -1;
        fetching = false;
        allReceived = false;
        firstPageFetched = false;
        allItems.clear();
        latestItems.clear();
        cancelFetching();
    }

    public void cancelFetching() {
        if (fetching && fetchingDisposable != null) {
            // reset limit
            offset -= limit;
            fetchingDisposable.dispose();
            fetchingDisposable = null;
            fetching = false;
        }
    }

    // OBSERVER
    private final PublishSubject<List<T>> allItemsPublisher = PublishSubject.create();

    /**
     * Observe item changes
     */
    public Observable<List<T>> observeAllItems() {
        return allItemsPublisher;
    }

    /**
     * Observe errors
     */
    private final PublishSubject<Throwable> errorPublisher = PublishSubject.create();
    public Observable<Throwable> observeError() {
        return errorPublisher;
    }

    /**
     * Observe latest item changes
     */
    private final PublishSubject<List<T>> latestItemsPublisher = PublishSubject.create();
    public Observable<List<T>> observeLatestItems() {
        return latestItemsPublisher;
    }

    // Getter

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @return the current offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @return total items (-1 if first page not fetched yet)
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return all items fetched yet
     */
    public List<T> getAllItems() {
        return allItems;
    }

    /**
     * @return latest fetched items
     */
    public List<T> getLatestItems() {
        return latestItems;
    }

    /**
     * @return fetching state
     */
    public boolean isFetching() {
        return fetching;
    }

    /**
     * @return all items received
     */
    public boolean allReceived() {
        return allReceived;
    }

    public boolean isFirstPageFetched() {
        return firstPageFetched;
    }

    public void saveInstanceState(Bundle outState) {
        if (outState == null) return;
        outState.putInt(KEY_LIMIT, limit);
        outState.putInt(KEY_OFFSET, offset);
        outState.putInt(KEY_TOTAL, total);
        outState.putBoolean(KEY_ALL_RECEIVED, allReceived);
        outState.putBoolean(KEY_FIRST_PAGE_FETCHED, firstPageFetched);
        outState.putParcelableArrayList(KEY_ALL_ITEMS, (ArrayList<? extends Parcelable>) allItems);
        outState.putParcelableArrayList(KEY_LATEST_ITEMS, (ArrayList<? extends Parcelable>) latestItems);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;
        limit = savedInstanceState.getInt(KEY_LIMIT);
        offset = savedInstanceState.getInt(KEY_OFFSET);
        total = savedInstanceState.getInt(KEY_TOTAL);
        allReceived = savedInstanceState.getBoolean(KEY_ALL_RECEIVED);
        firstPageFetched = savedInstanceState.getBoolean(KEY_FIRST_PAGE_FETCHED);
        allItems = savedInstanceState.getParcelableArrayList(KEY_ALL_ITEMS);
        latestItems = savedInstanceState.getParcelableArrayList(KEY_LATEST_ITEMS);

        // restore options
        options.put(SpotifyService.QUERY_PARAMETER.LIMIT, limit);
        options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);
    }

    public static class Builder<T extends Parcelable> {

        private Fetcher<T> fetcher;
        private int limit = -1;
        private int offset = 0;
        private HashMap<String, Object> defaultOptions;

        /**
         * Will be called every time we need new items you should apply the options in the call
         * @param fetcher the fetcher
         */
        public Builder<T> fetcher(@NonNull Fetcher<T> fetcher) {
            this.fetcher = fetcher;
            return this;
        }

        /**
         * @param limit the limit per page
         */
        public Builder<T> limit(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * @param offset the initial offset
         */
        public Builder<T> offset(int offset) {
            this.offset = offset;
            return this;
        }

        /**
         * @param defaultOptions they will be passed in every fetch call
         */
        public Builder<T> defaultOptions(@NonNull HashMap<String, Object> defaultOptions) {
            this.defaultOptions = defaultOptions;
            return this;
        }

        public PaginationHelper<T> build() {
            if (fetcher == null) {
                throw new IllegalStateException("missing fetcher");
            }
            if (limit == -1) {
                throw new IllegalStateException("missing limit");
            }

            return new PaginationHelper<>(this);
        }
    }

    public interface Fetcher<T extends Parcelable> {
        Observable<Pager<T>> fetch(@NonNull HashMap<String, Object> options);
    }
}

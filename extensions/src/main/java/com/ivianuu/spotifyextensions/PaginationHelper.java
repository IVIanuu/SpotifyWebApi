/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ivianuu.spotifyextensions;

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

    private HashMap<String, Object> options;

    private int limit;
    private int offset;
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
        if (options == null) options = new HashMap<>(); // create options if necessary
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
    public void fetchNextPage() {
        Log.d(TAG, "next page");
        if (allReceived || fetching) {
            Log.d(TAG, "should not fetch next age");
            // load only if not all items are received and we not already fetching a page
            return;
        }

        // update options
        offset += limit;
        options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);

        // fetch
        fetchingDisposable = fetcher.fetch(options)
                .subscribe(new Consumer<Pager<T>>() {
                    @Override
                    public void accept(Pager<T> tPager) throws Exception {
                        Log.d(TAG, "fetch success");
                        // update lists
                        allItems.addAll(tPager.items);

                        latestItems.clear();
                        latestItems.addAll(tPager.items);

                        allReceived = tPager.next == null;
                        if (!firstPageFetched) firstPageFetched = true;

                        fetching = false;

                        allItemsPublisher.onNext(allItems);
                        latestItemsPublisher.onNext(latestItems);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // undo offset change
                        offset -= limit;
                        options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);

                        // forward errors
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

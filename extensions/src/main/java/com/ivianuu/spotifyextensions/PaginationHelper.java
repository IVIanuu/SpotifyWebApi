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

import android.support.annotation.NonNull;
import android.util.Log;

import com.ivianuu.spotifywebapi.SpotifyService;
import com.ivianuu.spotifywebapi.model.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * A class which helps with pagination
 */

// TODO: 06.06.2017 WIP
public final class PaginationHelper<T> {

    private static final String TAG = PaginationHelper.class.getSimpleName();

    private HashMap<String, Object> options;

    private int limit;
    private int offset;

    private Fetcher<T> fetcher;
    private Disposable fetchingDisposable;

    private boolean fetching;
    private boolean allReceived;
    private boolean firstPageFetched;

    private List<T> allItems = new ArrayList<>();

    private PaginationHelper(Builder<T> builder) {
        this.options = builder.defaultOptions;
        if (options == null) options = new HashMap<>(); // create options if necessary
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.fetcher = builder.fetcher;

        options.put(SpotifyService.QUERY_PARAMETER.LIMIT, limit);
    }

    /**
     * Fetches the next page
     */
    public boolean fetchNextPage() {
        if (allReceived || fetching) {
            // load only if not all items are received and we not already fetching a page
            return false;
        }

        if (firstPageFetched) {
            // update options
            offset += limit;
            options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);
        }

        // fetch
        fetchingDisposable = fetcher.fetch(options).toObservable()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        fetching = true;
                        fetchingSubject.onNext(true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        fetching = false;
                        fetchingSubject.onNext(false);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        allItemsSubject.onNext(allItems);
                    }
                })
                .subscribe(new Consumer<Pager<T>>() {
                    @Override
                    public void accept(Pager<T> tPager) throws Exception {
                        // update lists
                        allItems.addAll(tPager.items());

                        allReceived = tPager.next() == null;
                        if (!firstPageFetched) firstPageFetched = true;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // undo offset change
                        offset -= limit;
                        options.put(SpotifyService.QUERY_PARAMETER.OFFSET, offset);

                        // forward errors
                        errorSubject.onNext(throwable);
                    }
                });

        return true;
    }

    /**
     * Resets this instance
     */
    public void reset() {
        offset = 0;
        fetching = false;
        allReceived = false;
        firstPageFetched = false;
        allItems.clear();
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
    private final PublishSubject<List<T>> allItemsSubject = PublishSubject.create();

    /**
     * Observe item changes
     */
    public Observable<List<T>> allItems() {
        return allItemsSubject;
    }

    /**
     * Observe errors
     */
    private final PublishSubject<Throwable> errorSubject = PublishSubject.create();
    public Observable<Throwable> error() {
        return errorSubject;
    }

    private final PublishSubject<Boolean> fetchingSubject = PublishSubject.create();
    public Observable<Boolean> fetching() {
        return fetchingSubject;
    }

    public static class Builder<T> {

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

    public interface Fetcher<T> {
        Single<Pager<T>> fetch(@NonNull HashMap<String, Object> options);
    }
}

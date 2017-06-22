package com.ivianuu.spotifywebapi.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ivianuu.spotifyextensions.PaginationHelper;
import com.ivianuu.spotifywebapi.SpotifyService;
import com.ivianuu.spotifywebapi.model.Album;
import com.ivianuu.spotifywebapi.model.AlbumSimple;
import com.ivianuu.spotifywebapi.model.NewReleases;
import com.ivianuu.spotifywebapi.model.Pager;
import com.ivianuu.spotifywebapi.model.Track;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public class PaginationActivity extends AppCompatActivity {

    private SpotifyService spotifyService;

    private PaginationHelper<AlbumSimple> paginationHelper;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final AlbumAdapter albumAdapter = new AlbumAdapter();
        recyclerView.setAdapter(albumAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
                        paginationHelper.fetchNextPage();
                    }
                }
            }
        });

        spotifyService = ((App) getApplicationContext()).getSpotifyService();

                paginationHelper = new PaginationHelper.Builder<AlbumSimple>()
                .limit(10)
                .fetcher(new PaginationHelper.Fetcher<AlbumSimple>() {
                    @Override
                    public Single<Pager<AlbumSimple>> fetch(@NonNull HashMap<String, Object> options) {
                        return spotifyService.getNewReleasesBody(options)
                                .subscribeOn(Schedulers.io())
                                .map(new Function<NewReleases, Pager<AlbumSimple>>() {
                                    @Override
                                    public Pager<AlbumSimple> apply(NewReleases newReleases) throws Exception {
                                        return newReleases.albums();
                                    }
                                }); // return the observable and pass in the provided options
                    }
                })
                .build();

        paginationHelper.observeLatestItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AlbumSimple>>() {
                    @Override
                    public void accept(List<AlbumSimple> albumSimples) throws Exception {
                        // add albums to our recycler view
                        albumAdapter.addAlbums(albumSimples);
                    }
                });

        // load first page
        paginationHelper.fetchNextPage();

        spotifyService.getTopTracksBody()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pager<Track>>() {
                    @Override
                    public void accept(Pager<Track> trackPager) throws Exception {
                        for (Track track : trackPager.items()) {
                            Log.d("testtt", track.name());
                        }
                    }
                });
    }

}

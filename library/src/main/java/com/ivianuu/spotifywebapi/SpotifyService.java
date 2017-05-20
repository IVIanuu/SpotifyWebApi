package com.ivianuu.spotifywebapi;

import com.ivianuu.spotifywebapi.model.Album;
import com.ivianuu.spotifywebapi.model.Albums;
import com.ivianuu.spotifywebapi.model.AlbumsPager;
import com.ivianuu.spotifywebapi.model.Artist;
import com.ivianuu.spotifywebapi.model.Artists;
import com.ivianuu.spotifywebapi.model.ArtistsCursorPager;
import com.ivianuu.spotifywebapi.model.ArtistsPager;
import com.ivianuu.spotifywebapi.model.AudioFeaturesTrack;
import com.ivianuu.spotifywebapi.model.CategoriesPager;
import com.ivianuu.spotifywebapi.model.Category;
import com.ivianuu.spotifywebapi.model.CurrentlyPlayingContext;
import com.ivianuu.spotifywebapi.model.FeaturedPlaylists;
import com.ivianuu.spotifywebapi.model.NewReleases;
import com.ivianuu.spotifywebapi.model.Pager;
import com.ivianuu.spotifywebapi.model.Payload;
import com.ivianuu.spotifywebapi.model.PlayHistory;
import com.ivianuu.spotifywebapi.model.Playlist;
import com.ivianuu.spotifywebapi.model.PlaylistFollowPrivacy;
import com.ivianuu.spotifywebapi.model.PlaylistSimple;
import com.ivianuu.spotifywebapi.model.PlaylistTrack;
import com.ivianuu.spotifywebapi.model.PlaylistsPager;
import com.ivianuu.spotifywebapi.model.Recommendations;
import com.ivianuu.spotifywebapi.model.Result;
import com.ivianuu.spotifywebapi.model.SavedAlbum;
import com.ivianuu.spotifywebapi.model.SavedTrack;
import com.ivianuu.spotifywebapi.model.SeedsGenres;
import com.ivianuu.spotifywebapi.model.SnapshotId;
import com.ivianuu.spotifywebapi.model.Track;
import com.ivianuu.spotifywebapi.model.Tracks;
import com.ivianuu.spotifywebapi.model.TracksPager;
import com.ivianuu.spotifywebapi.model.TracksToRemove;
import com.ivianuu.spotifywebapi.model.TracksToRemoveWithPosition;
import com.ivianuu.spotifywebapi.model.UserPrivate;
import com.ivianuu.spotifywebapi.model.UserPublic;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SpotifyService {

    String BASE_URL = "https://api.spotify.com/v1/";

    String LIMIT = "limit";

    String OFFSET = "offset";

    String ALBUM_TYPE = "album_type";

    String TYPE = "type";

    String MARKET = "market";

    String COUNTRY = "country";

    String LOCALE = "locale";

    String TIMESTAMP = "timestamp";

    String TIME_RANGE = "time_range";

    String FROM_TOKEN = "from_token";

    String AFTER = "after";

    String URIS = "uris";

    String IDS = "ids";

    String NAME = "name";

    String DEVICE_ID = "device_id";

    String CONTEXT_URI = "context_uri";

    String DESCRIPTION = "description";

    String PUBLIC = "public";

    String COLLABORATIVE = "collaborative";

    String POSITION = "position";

    /************
     * Profiles *
     ************/

    @GET("me")
    Call<UserPrivate> getMe();

    @GET("users/{id}")
    Call<UserPublic> getUser(@Path("id") String userId);


    /*************
     * Playlists *
     *************/

    @GET("me/playlists")
    Call<Pager<PlaylistSimple>> getMyPlaylists();

    @GET("me/playlists")
    Call<Pager<Playlist>> getMyPlaylists(@QueryMap Map<String, Object> options);

    @GET("users/{id}/playlists")
    Call<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId, @QueryMap Map<String, Object> options);

    @GET("users/{id}/playlists")
    Call<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId);

    @GET("users/{user_id}/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @POST("users/{user_id}/playlists")
    Call<Playlist> createPlaylist(@Path("user_id") String userId, @Body Map<String, Object> options);

    @POST("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> queryParameters, @Body Map<String, Object> body);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Result> replaceTracksInPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Body Object body);

    @PUT("users/{user_id}/playlists/{playlist_id}")
    Call<Result> changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body PlaylistFollowPrivacy playlistFollowPrivacy);

    @DELETE("users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> unfollowPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> reorderPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    /**********
     * Albums *
     **********/

    @GET("albums/{id}")
    Call<Album> getAlbum(@Path("id") String albumId);

    @GET("albums/{id}")
    Call<Album> getAlbum(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    @GET("albums")
    Call<Albums> getAlbums(@Query("ids") String albumIds);

    @GET("albums")
    Call<Albums> getAlbums(@Query("ids") String albumIds, @QueryMap Map<String, Object> options);

    @GET("albums/{id}/tracks")
    Call<Pager<Track>> getAlbumTracks(@Path("id") String albumId);

    @GET("albums/{id}/tracks")
    Call<Pager<Track>> getAlbumTracks(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    /***********
     * Artists *
     ***********/

    @GET("artists/{id}")
    Call<Artist> getArtist(@Path("id") String artistId);

    @GET("artists")
    Call<Artists> getArtists(@Query("ids") String artistIds);

    @GET("artists/{id}/albums")
    Call<Pager<Album>> getArtistAlbums(@Path("id") String artistId);

    @GET("artists/{id}/albums")
    Call<Pager<Album>> getArtistAlbums(@Path("id") String artistId, @QueryMap Map<String, Object> options);

    @GET("artists/{id}/top-tracks")
    Call<Tracks> getArtistTopTrack(@Path("id") String artistId, @Query(COUNTRY) String country);

    @GET("artists/{id}/related-artists")
    Call<Artists> getRelatedArtists(@Path("id") String artistId);

    /**********
     * Tracks *
     **********/

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String trackId);

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String trackId, @QueryMap Map<String, Object> options);

    @GET("tracks")
    Call<Tracks> getTracks(@Query("ids") String trackIds);

    @GET("tracks")
    Call<Tracks> getTracks(@Query("ids") String trackIds, @QueryMap Map<String, Object> options);

    /**********
     * Browse *
     **********/

    @GET("browse/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists();

    @GET("browse/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists(@QueryMap Map<String, Object> options);

    @GET("browse/new-releases")
    Call<NewReleases> getNewReleases();

    @GET("browse/new-releases")
    Call<NewReleases> getNewReleases(@QueryMap Map<String, Object> options);

    @GET("browse/categories")
    Call<CategoriesPager> getCategories(@QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}/playlists")
    Call<PlaylistsPager> getPlaylistsForCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    /************************
     * Library / Your Music *
     ************************/

    @GET("me/tracks")
    Call<Pager<SavedTrack>> getMySavedTracks();

    @GET("me/tracks")
    Call<Pager<SavedTrack>> getMySavedTracks(@QueryMap Map<String, Object> options);

    @GET("me/tracks/contains")
    Call<Boolean[]> containsMySavedTracks(@Query("ids") String ids);

    @PUT("me/tracks")
    Call<Void> addToMySavedTracks(@Query("ids") String ids);

    @DELETE("me/tracks")
    Call<Void> removeFromMySavedTracks(@Query("ids") String ids);

    @GET("me/albums")
    Call<Pager<SavedAlbum>> getMySavedAlbums();

    @GET("me/albums")
    Call<Pager<SavedAlbum>> getMySavedAlbums(@QueryMap Map<String, Object> options);

    @GET("me/albums/contains")
    Call<Boolean[]> containsMySavedAlbums(@Query("ids") String ids);

    @PUT("me/albums")
    Call<Void> addToMySavedAlbums(@Query("ids") String ids);

    @DELETE("me/albums")
    Call<Void> removeFromMySavedAlbums(@Query("ids") String ids);

    /**********
     * Follow *
     **********/

    @PUT("me/following?type=user")
    Call<Result> followUsers(@Query("ids") String ids);

    @PUT("me/following?type=artist")
    Call<Void> followArtists(@Query("ids") String ids);

    @DELETE("me/following?type=user")
    Call<Result> unfollowUsers(@Query("ids") String ids);

    @DELETE("me/following?type=artist")
    Call<Void> unfollowArtists(@Query("ids") String ids);

    @GET("me/following/contains?type=user")
    Call<Boolean[]> isFollowingUsers(@Query("ids") String ids);

    @GET("me/following/contains?type=artist")
    Call<Boolean[]> isFollowingArtists(@Query("ids") String ids);

    @GET("users/{user_id}/playlists/{playlist_id}/followers/contains")
    Call<Boolean[]> areFollowingPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("ids") String ids);

    @GET("me/following?type=artist")
    Call<ArtistsCursorPager> getFollowedArtists();

    @GET("me/following?type=artist")
    Call<ArtistsCursorPager> getFollowedArtists(@QueryMap Map<String, Object> options);

    /**********
     * Search *
     **********/

    @GET("search?type=track")
    Call<TracksPager> searchTracks(@Query("q") String q);

    @GET("search?type=track")
    Call<TracksPager> searchTracks(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=artist")
    Call<ArtistsPager> searchArtists(@Query("q") String q);

    @GET("search?type=artist")
    Call<ArtistsPager> searchArtists(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=album")
    Call<AlbumsPager> searchAlbums(@Query("q") String q);

    @GET("search?type=album")
    Call<AlbumsPager> searchAlbums(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=playlist")
    Call<PlaylistsPager> searchPlaylists(@Query("q") String q);

    @GET("search?type=playlist")
    Call<PlaylistsPager> searchPlaylists(@Query("q") String q, @QueryMap Map<String, Object> options);

    /******************
     * Audio features *
     ******************/

    @GET("audio-features")
    Call<AudioFeaturesTrack> getTracksAudioFeatures(@Query("ids") String ids);

    @GET("audio-features/{id}")
    Call<AudioFeaturesTrack> getTrackAudioFeatures(@Path("id") String id);

    /*******************
     * Recommendations *
     *******************/

    @GET("recommendations")
    Call<Recommendations> getRecommendations(@QueryMap Map<String, Object> options);

    @GET("recommendations/available-genre-seeds")
    Call<SeedsGenres> getSeedsGenres();

    /*****************************
     * User Top Artists & Tracks *
     *****************************/

    @GET("me/top/artists")
    Call<Pager<Artist>> getTopArtists();

    @GET("me/top/artists")
    Call<Pager<Artist>> getTopArtists(@QueryMap Map<String, Object> options);

    @GET("me/top/tracks")
    Call<Pager<Track>> getTopTracks();

    @GET("me/top/tracks")
    Call<Pager<Track>> getTopTracks(@QueryMap Map<String, Object> options);

    /*****************************
     * Player *
     *****************************/

    @GET("me/player/recently-played")
    Call<Pager<PlayHistory>> getRecentlyPlayedTracks();

    @GET("me/player/recently-played")
    Call<Pager<PlayHistory>> getRecentlyPlayedTracks(@QueryMap Map<String, Object> options);

    @GET("me/player/devices")
    Call<Payload> getUsersAvailableDevices();

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentPlayback();

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentPlayback(@QueryMap Map<String, Object> options);

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentlyPlayingTrack();

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentlyPlayingTrack(@QueryMap Map<String, Object> options);

    @PUT("me/player")
    Call<Result> transferUserPlayback(@Body Map<String, Object> body);

    @PUT("me/player/play")
    Call<Result> startUserPlayback(@Body TracksToRemove body);

    @PUT("me/player/play")
    Call<Result> startUserPlayback(@Query("device_id") String device_id, @Body Map<String, Object> body);

    @PUT("me/player/play")
    Call<Result> resumeUserPlayback();

    @PUT("me/player/play")
    Call<Result> resumeUserPlayback(@Query("device_id") String device_id);

    @PUT("me/player/pause")
    Call<Result> pauseUserPlayback();

    @PUT("me/player/pause")
    Call<Result> pauseUserPlayback(@Query("device_id") String device_id);

    @PUT("me/player/next")
    Call<Result> skipToTheNextTrack();

    @PUT("me/player/next")
    Call<Result> skipToTheNextTrack(@Query("device_id") String device_id);

    @PUT("me/player/previous")
    Call<Result> skipToThePreviousTrack();

    @PUT("me/player/previous")
    Call<Result> skipToThePreviousTrack(@Query("device_id") String device_id);

    @PUT("me/player/seek")
    Call<Result> seekToPositionInCurrentTrack(@Query("position_ms") int position_ms);

    @PUT("me/player/seek")
    Call<Result> seekToPositionInCurrentTrack(@Query("position_ms") int position_ms, @Query("device_id") String device_id);

    @PUT("me/player/repeat")
    Call<Result> setRepeatMode(@Query("state") String state);

    @PUT("me/player/repeat")
    Call<Result> setRepeatMode(@Query("state") String state, @Query("device_id") String device_id);

    @PUT("me/player/volume")
    Call<Result> setVolume(@Query("volume_percent") int volume_percent);

    @PUT("me/player/volume")
    Call<Result> setVolume(@Query("volume_percent") int volume_percent, @Query("device_id") String device_id);

    @PUT("me/player/shuffle")
    Call<Result> toggleShuffle(@Query("state") boolean state);

    @PUT("me/player/shuffle")
    Call<Result> toggleShuffle(@Query("state") boolean state, @Query("device_id") String device_id);

    class Builder {

        private String accessToken;

        private Retrofit.Builder retrofitBuilder;

        private OkHttpClient.Builder okHttpClientBuilder;

        public Builder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder withRetrofitBuilder(Retrofit.Builder retrofitBuilder) {
            this.retrofitBuilder = retrofitBuilder;
            return this;
        }

        public Builder withOkHttpClientBuilder(OkHttpClient.Builder okHttpClientBuilder) {
            this.okHttpClientBuilder = okHttpClientBuilder;
            return this;
        }

        public SpotifyService build() {
            if (retrofitBuilder == null) {
                retrofitBuilder = new Retrofit.Builder();
            }

            if (okHttpClientBuilder == null) {
                okHttpClientBuilder = new OkHttpClient.Builder();
            }

            if (accessToken != null) {
                okHttpClientBuilder.addInterceptor(new AuthInterceptor(accessToken));
            }

            Retrofit restAdapter = retrofitBuilder
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();

            return restAdapter.create(SpotifyService.class);
        }
    }
}

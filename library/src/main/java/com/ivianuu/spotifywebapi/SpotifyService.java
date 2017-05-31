package com.ivianuu.spotifywebapi;

import com.ivianuu.dynamiccalladapter.DynamicCall;
import com.ivianuu.dynamiccalladapter.DynamicCallAdapterFactory;
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
import com.ivianuu.spotifywebapi.model.TracksToRemoveByPosition;
import com.ivianuu.spotifywebapi.model.TracksToRemoveWithPosition;
import com.ivianuu.spotifywebapi.model.UserPrivate;
import com.ivianuu.spotifywebapi.model.UserPublic;

import java.util.Map;

import okhttp3.OkHttpClient;
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

import static com.ivianuu.spotifywebapi.SpotifyService.QUERY_PARAMETER.COUNTRY;

public interface SpotifyService {

    String BASE_URL = "https://api.spotify.com/v1/";

    /*************
     * Albums *
     *************/

    @GET("albums/{id}")
    DynamicCall<Album> getAlbum(@Path("id") String albumId);

    @GET("albums/{id}")
    DynamicCall<Album> getAlbum(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    @GET("albums")
    DynamicCall<Albums> getAlbums(@Query("ids") String albumIds);

    @GET("albums")
    DynamicCall<Albums> getAlbums(@Query("ids") String albumIds, @QueryMap Map<String, Object> options);

    @GET("albums/{id}/tracks")
    DynamicCall<Pager<Track>> getAlbumTracks(@Path("id") String albumId);

    @GET("albums/{id}/tracks")
    DynamicCall<Pager<Track>> getAlbumTracks(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    /*************
     * Artists *
     *************/

    @GET("artists/{id}")
    DynamicCall<Artist> getArtist(@Path("id") String artistId);

    @GET("artists")
    DynamicCall<Artists> getArtists(@Query("ids") String artistIds);

    @GET("artists/{id}/albums")
    DynamicCall<Pager<Album>> getArtistAlbums(@Path("id") String artistId);

    @GET("artists/{id}/albums")
    DynamicCall<Pager<Album>> getArtistAlbums(@Path("id") String artistId, @QueryMap Map<String, Object> options);

    @GET("artists/{id}/top-tracks")
    DynamicCall<Tracks> getArtistTopTrack(@Path("id") String artistId, @Query(COUNTRY) String country);

    @GET("artists/{id}/related-artists")
    DynamicCall<Artists> getRelatedArtists(@Path("id") String artistId);

    /*************
     * Browse *
     *************/

    @GET("browse/featured-playlists")
    DynamicCall<FeaturedPlaylists> getFeaturedPlaylists();

    @GET("browse/featured-playlists")
    DynamicCall<FeaturedPlaylists> getFeaturedPlaylists(@QueryMap Map<String, Object> options);

    @GET("browse/new-releases")
    DynamicCall<NewReleases> getNewReleases();

    @GET("browse/new-releases")
    DynamicCall<NewReleases> getNewReleases(@QueryMap Map<String, Object> options);

    @GET("browse/categories")
    DynamicCall<CategoriesPager> getCategories(@QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}")
    DynamicCall<Category> getCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}/playlists")
    DynamicCall<PlaylistsPager> getPlaylistsForCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("recommendations")
    DynamicCall<Recommendations> getRecommendations(@QueryMap Map<String, Object> options);

    @GET("recommendations/available-genre-seeds")
    DynamicCall<SeedsGenres> getSeedsGenres();

    /*************
     * Follow *
     *************/

    @GET("me/following?type=artist")
    DynamicCall<ArtistsCursorPager> getFollowedArtists();

    @GET("me/following?type=artist")
    DynamicCall<ArtistsCursorPager> getFollowedArtists(@QueryMap Map<String, Object> options);

    @PUT("me/following?type=artist")
    DynamicCall<Void> followArtists(@Query("ids") String ids);

    @PUT("me/following?type=user")
    DynamicCall<Result> followUsers(@Query("ids") String ids);

    @DELETE("me/following?type=artist")
    DynamicCall<Void> unfollowArtists(@Query("ids") String ids);

    @DELETE("me/following?type=user")
    DynamicCall<Result> unfollowUsers(@Query("ids") String ids);

    @GET("me/following/contains?type=user")
    DynamicCall<Boolean[]> isFollowingUsers(@Query("ids") String ids);

    @GET("me/following/contains?type=artist")
    DynamicCall<Boolean[]> isFollowingArtists(@Query("ids") String ids);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    DynamicCall<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    DynamicCall<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body PlaylistFollowPrivacy playlistFollowPrivacy);

    @DELETE("users/{user_id}/playlists/{playlist_id}/followers")
    DynamicCall<Result> unfollowPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}/followers/contains")
    DynamicCall<Boolean[]> areFollowingPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("ids") String ids);

    /*************
     * Library *
     *************/

    @PUT("me/tracks")
    DynamicCall<Void> addToMySavedTracks(@Query("ids") String ids);

    @GET("me/tracks")
    DynamicCall<Pager<SavedTrack>> getMySavedTracks();

    @GET("me/tracks")
    DynamicCall<Pager<SavedTrack>> getMySavedTracks(@QueryMap Map<String, Object> options);

    @DELETE("me/tracks")
    DynamicCall<Void> removeFromMySavedTracks(@Query("ids") String ids);

    @GET("me/tracks/contains")
    DynamicCall<Boolean[]> containsMySavedTracks(@Query("ids") String ids);

    @PUT("me/albums")
    DynamicCall<Void> addToMySavedAlbums(@Query("ids") String ids);

    @GET("me/albums")
    DynamicCall<Pager<SavedAlbum>> getMySavedAlbums();

    @GET("me/albums")
    DynamicCall<Pager<SavedAlbum>> getMySavedAlbums(@QueryMap Map<String, Object> options);

    @DELETE("me/albums")
    DynamicCall<Void> removeFromMySavedAlbums(@Query("ids") String ids);

    @GET("me/albums/contains")
    DynamicCall<Boolean[]> containsMySavedAlbums(@Query("ids") String ids);

    /*************
     * Personalization *
     *************/

    @GET("me/top/artists")
    DynamicCall<Pager<Artist>> getTopArtists();

    @GET("me/top/artists")
    DynamicCall<Pager<Artist>> getTopArtists(@QueryMap Map<String, Object> options);

    @GET("me/top/tracks")
    DynamicCall<Pager<Track>> getTopTracks();

    @GET("me/top/tracks")
    DynamicCall<Pager<Track>> getTopTracks(@QueryMap Map<String, Object> options);

    @GET("me/player/recently-played")
    DynamicCall<Pager<PlayHistory>> getRecentlyPlayedTracks();

    @GET("me/player/recently-played")
    DynamicCall<Pager<PlayHistory>> getRecentlyPlayedTracks(@QueryMap Map<String, Object> options);

    /*************
     * Player *
     *************/

    @GET("me/player/devices")
    DynamicCall<Payload> getUsersAvailableDevices();

    @GET("me/player")
    DynamicCall<CurrentlyPlayingContext> getCurrentPlayback();

    @GET("me/player")
    DynamicCall<CurrentlyPlayingContext> getCurrentPlayback(@QueryMap Map<String, Object> options);

    @GET("me/player")
    DynamicCall<CurrentlyPlayingContext> getCurrentlyPlayingTrack();

    @GET("me/player")
    DynamicCall<CurrentlyPlayingContext> getCurrentlyPlayingTrack(@QueryMap Map<String, Object> options);

    @PUT("me/player")
    DynamicCall<Result> transferUserPlayback(@Body Map<String, Object> body);

    @PUT("me/player/play")
    DynamicCall<Result> startUserPlayback(@Body TracksToRemove body);

    @PUT("me/player/play")
    DynamicCall<Result> startUserPlayback(@Query("device_id") String device_id, @Body Map<String, Object> body);

    @PUT("me/player/play")
    DynamicCall<Result> resumeUserPlayback();

    @PUT("me/player/play")
    DynamicCall<Result> resumeUserPlayback(@Query("device_id") String device_id);

    @PUT("me/player/pause")
    DynamicCall<Result> pauseUserPlayback();

    @PUT("me/player/pause")
    DynamicCall<Result> pauseUserPlayback(@Query("device_id") String device_id);

    @PUT("me/player/next")
    DynamicCall<Result> skipToTheNextTrack();

    @PUT("me/player/next")
    DynamicCall<Result> skipToTheNextTrack(@Query("device_id") String device_id);

    @PUT("me/player/previous")
    DynamicCall<Result> skipToThePreviousTrack();

    @PUT("me/player/previous")
    DynamicCall<Result> skipToThePreviousTrack(@Query("device_id") String device_id);

    @PUT("me/player/seek")
    DynamicCall<Result> seekToPositionInCurrentTrack(@Query("position_ms") int position_ms);

    @PUT("me/player/seek")
    DynamicCall<Result> seekToPositionInCurrentTrack(@Query("position_ms") int position_ms, @Query("device_id") String device_id);

    @PUT("me/player/repeat")
    DynamicCall<Result> setRepeatMode(@Query("state") String state);

    @PUT("me/player/repeat")
    DynamicCall<Result> setRepeatMode(@Query("state") String state, @Query("device_id") String device_id);

    @PUT("me/player/volume")
    DynamicCall<Result> setVolume(@Query("volume_percent") int volume_percent);

    @PUT("me/player/volume")
    DynamicCall<Result> setVolume(@Query("volume_percent") int volume_percent, @Query("device_id") String device_id);

    @PUT("me/player/shuffle")
    DynamicCall<Result> toggleShuffle(@Query("state") boolean state);

    @PUT("me/player/shuffle")
    DynamicCall<Result> toggleShuffle(@Query("state") boolean state, @Query("device_id") String device_id);

    /*************
     * Playlists *
     *************/

    @GET("users/{id}/playlists")
    DynamicCall<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId, @QueryMap Map<String, Object> options);

    @GET("users/{id}/playlists")
    DynamicCall<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId);

    @GET("me/playlists")
    DynamicCall<Pager<PlaylistSimple>> getMyPlaylists();

    @GET("me/playlists")
    DynamicCall<Pager<Playlist>> getMyPlaylists(@QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}")
    DynamicCall<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}")
    DynamicCall<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @POST("users/{user_id}/playlists")
    DynamicCall<Playlist> createPlaylist(@Path("user_id") String userId, @Body Map<String, Object> options);

    @PUT("users/{user_id}/playlists/{playlist_id}")
    DynamicCall<Result> changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @POST("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<SnapshotId> addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> queryParameters, @Body Map<String, Object> body);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition);

    @DELETE("/users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveByPosition tracksToRemoveByPosition);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<SnapshotId> reorderPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    DynamicCall<Result> replaceTracksInPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Body Object body);

    /************
     * Profiles *
     ************/

    @GET("me")
    DynamicCall<UserPrivate> getMe();

    @GET("users/{id}")
    DynamicCall<UserPublic> getUser(@Path("id") String userId);

    /*************
     * Search *
     *************/

    @GET("search?type=track")
    DynamicCall<TracksPager> searchTracks(@Query("q") String q);

    @GET("search?type=track")
    DynamicCall<TracksPager> searchTracks(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=artist")
    DynamicCall<ArtistsPager> searchArtists(@Query("q") String q);

    @GET("search?type=artist")
    DynamicCall<ArtistsPager> searchArtists(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=album")
    DynamicCall<AlbumsPager> searchAlbums(@Query("q") String q);

    @GET("search?type=album")
    DynamicCall<AlbumsPager> searchAlbums(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=playlist")
    DynamicCall<PlaylistsPager> searchPlaylists(@Query("q") String q);

    @GET("search?type=playlist")
    DynamicCall<PlaylistsPager> searchPlaylists(@Query("q") String q, @QueryMap Map<String, Object> options);

    /*************
     * Tracks *
     *************/

    @GET("tracks/{id}")
    DynamicCall<Track> getTrack(@Path("id") String trackId);

    @GET("tracks/{id}")
    DynamicCall<Track> getTrack(@Path("id") String trackId, @QueryMap Map<String, Object> options);

    @GET("tracks")
    DynamicCall<Tracks> getTracks(@Query("ids") String trackIds);

    @GET("tracks")
    DynamicCall<Tracks> getTracks(@Query("ids") String trackIds, @QueryMap Map<String, Object> options);

    @GET("audio-features")
    DynamicCall<AudioFeaturesTrack> getTracksAudioFeatures(@Query("ids") String ids);

    @GET("audio-features/{id}")
    DynamicCall<AudioFeaturesTrack> getTrackAudioFeatures(@Path("id") String id);

    interface QUERY_PARAMETER {
        String LIMIT = "limit";

        String OFFSET = "offset";

        String ALBUM_TYPE = "album_type";

        String TYPE = "type";

        String MARKET = "market";

        String COUNTRY = "country";

        String LOCALE = "locale";

        String FIELDS = "fields";

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
    }

    class Builder {

        private String accessToken;

        private Retrofit.Builder retrofitBuilder;

        private OkHttpClient.Builder okHttpClientBuilder;

        public Builder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder withRetrofit(Retrofit retrofit) {
            this.retrofitBuilder = retrofit.newBuilder();
            return this;
        }

        public Builder withOkHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClientBuilder = okHttpClient.newBuilder();
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

            DynamicCallAdapterFactory dynamicCallAdapterFactory = new DynamicCallAdapterFactory.Builder()
                    .build();

            Retrofit restAdapter = retrofitBuilder
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(dynamicCallAdapterFactory)
                    .client(okHttpClientBuilder.build())
                    .build();

            return restAdapter.create(SpotifyService.class);
        }
    }
}

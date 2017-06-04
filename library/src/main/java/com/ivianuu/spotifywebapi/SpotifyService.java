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
import com.ivianuu.spotifywebapi.model.TracksToRemoveByPosition;
import com.ivianuu.spotifywebapi.model.TracksToRemoveWithPosition;
import com.ivianuu.spotifywebapi.model.UserPrivate;
import com.ivianuu.spotifywebapi.model.UserPublic;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
    Call<Album> getAlbum(@Path("id") String albumId);

    @GET("albums/{id}")
    Observable<Album> getAlbumRx(@Path("id") String albumId);

    @GET("albums/{id}")
    Call<Album> getAlbum(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    @GET("albums/{id}")
    Observable<Album> getAlbumRx(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    @GET("albums")
    Call<Albums> getAlbums(@Query("ids") String albumIds);

    @GET("albums")
    Observable<Albums> getAlbumsRx(@Query("ids") String albumIds);

    @GET("albums")
    Call<Albums> getAlbums(@Query("ids") String albumIds, @QueryMap Map<String, Object> options);

    @GET("albums")
    Observable<Albums> getAlbumsRx(@Query("ids") String albumIds, @QueryMap Map<String, Object> options);

    @GET("albums/{id}/tracks")
    Call<Pager<Track>> getAlbumTracks(@Path("id") String albumId);

    @GET("albums/{id}/tracks")
    Observable<Pager<Track>> getAlbumTracksRx(@Path("id") String albumId);

    @GET("albums/{id}/tracks")
    Call<Pager<Track>> getAlbumTracks(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    @GET("albums/{id}/tracks")
    Observable<Pager<Track>> getAlbumTracksRx(@Path("id") String albumId, @QueryMap Map<String, Object> options);

    /*************
     * Artists *
     *************/

    @GET("artists/{id}")
    Call<Artist> getArtist(@Path("id") String artistId);

    @GET("artists/{id}")
    Observable<Artist> getArtistRx(@Path("id") String artistId);

    @GET("artists")
    Call<Artists> getArtists(@Query("ids") String artistIds);

    @GET("artists")
    Observable<Artists> getArtistsRx(@Query("ids") String artistIds);

    @GET("artists/{id}/albums")
    Call<Pager<Album>> getArtistAlbums(@Path("id") String artistId);

    @GET("artists/{id}/albums")
    Observable<Pager<Album>> getArtistAlbumsRx(@Path("id") String artistId);

    @GET("artists/{id}/albums")
    Call<Pager<Album>> getArtistAlbums(@Path("id") String artistId, @QueryMap Map<String, Object> options);

    @GET("artists/{id}/albums")
    Observable<Pager<Album>> getArtistAlbumsRx(@Path("id") String artistId, @QueryMap Map<String, Object> options);

    @GET("artists/{id}/top-tracks")
    Call<Tracks> getArtistTopTracks(@Path("id") String artistId, @Query(COUNTRY) String country);

    @GET("artists/{id}/top-tracks")
    Observable<Tracks> getArtistTopTracksRx(@Path("id") String artistId, @Query(COUNTRY) String country);

    @GET("artists/{id}/related-artists")
    Call<Artists> getRelatedArtists(@Path("id") String artistId);

    @GET("artists/{id}/related-artists")
    Observable<Artists> getRelatedArtistsRx(@Path("id") String artistId);

    /*************
     * Browse *
     *************/

    @GET("browse/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists();

    @GET("browse/featured-playlists")
    Observable<FeaturedPlaylists> getFeaturedPlaylistsRx();

    @GET("browse/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists(@QueryMap Map<String, Object> options);

    @GET("browse/featured-playlists")
    Observable<FeaturedPlaylists> getFeaturedPlaylistsRx(@QueryMap Map<String, Object> options);

    @GET("browse/new-releases")
    Call<NewReleases> getNewReleases();

    @GET("browse/new-releases")
    Observable<NewReleases> getNewReleasesRx();

    @GET("browse/new-releases")
    Call<NewReleases> getNewReleases(@QueryMap Map<String, Object> options);

    @GET("browse/new-releases")
    Observable<NewReleases> getNewReleasesRx(@QueryMap Map<String, Object> options);

    @GET("browse/categories")
    Call<CategoriesPager> getCategories(@QueryMap Map<String, Object> options);

    @GET("browse/categories")
    Observable<CategoriesPager> getCategoriesRx(@QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}")
    Observable<Category> getCategoryRx(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}/playlists")
    Call<PlaylistsPager> getPlaylistsForCategory(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("browse/categories/{category_id}/playlists")
    Observable<PlaylistsPager> getPlaylistsForCategoryRx(@Path("category_id") String categoryId, @QueryMap Map<String, Object> options);

    @GET("recommendations")
    Call<Recommendations> getRecommendations(@QueryMap Map<String, Object> options);

    @GET("recommendations")
    Observable<Recommendations> getRecommendationsRx(@QueryMap Map<String, Object> options);

    @GET("recommendations/available-genre-seeds")
    Call<SeedsGenres> getSeedsGenres();

    @GET("recommendations/available-genre-seeds")
    Observable<SeedsGenres> getSeedsGenresRx();

    /*************
     * Follow *
     *************/

    @GET("me/following?type=artist")
    Call<ArtistsCursorPager> getFollowedArtists();

    @GET("me/following?type=artist")
    Observable<ArtistsCursorPager> getFollowedArtistsRx();

    @GET("me/following?type=artist")
    Call<ArtistsCursorPager> getFollowedArtists(@QueryMap Map<String, Object> options);

    @GET("me/following?type=artist")
    Observable<ArtistsCursorPager> getFollowedArtistsRx(@QueryMap Map<String, Object> options);

    @PUT("me/following?type=artist")
    Call<Result> followArtists(@Query("ids") String ids);

    @PUT("me/following?type=artist")
    Observable<Result> followArtistsRx(@Query("ids") String ids);

    @PUT("me/following?type=user")
    Call<Result> followUsers(@Query("ids") String ids);

    @PUT("me/following?type=user")
    Observable<Result> followUsersRx(@Query("ids") String ids);

    @DELETE("me/following?type=artist")
    Call<Result> unfollowArtists(@Query("ids") String ids);

    @DELETE("me/following?type=artist")
    Observable<Result> unfollowArtistsRx(@Query("ids") String ids);

    @DELETE("me/following?type=user")
    Call<Result> unfollowUsers(@Query("ids") String ids);

    @DELETE("me/following?type=user")
    Observable<Result> unfollowUsersRx(@Query("ids") String ids);

    @GET("me/following/contains?type=user")
    Call<Boolean[]> isFollowingUsers(@Query("ids") String ids);

    @GET("me/following/contains?type=user")
    Observable<Boolean[]> isFollowingUsersRx(@Query("ids") String ids);

    @GET("me/following/contains?type=artist")
    Call<Boolean[]> isFollowingArtists(@Query("ids") String ids);

    @GET("me/following/contains?type=artist")
    Observable<Boolean[]> isFollowingArtistsRx(@Query("ids") String ids);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    Observable<Result> followPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> followPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body PlaylistFollowPrivacy playlistFollowPrivacy);

    @PUT("users/{user_id}/playlists/{playlist_id}/followers")
    Observable<Result> followPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body PlaylistFollowPrivacy playlistFollowPrivacy);

    @DELETE("users/{user_id}/playlists/{playlist_id}/followers")
    Call<Result> unfollowPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @DELETE("users/{user_id}/playlists/{playlist_id}/followers")
    Observable<Result> unfollowPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}/followers/contains")
    Call<Boolean[]> areFollowingPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("ids") String ids);

    @GET("users/{user_id}/playlists/{playlist_id}/followers/contains")
    Observable<Boolean[]> areFollowingPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("ids") String ids);

    /*************
     * Library *
     *************/

    @PUT("me/tracks")
    Call<Result> addToMySavedTracks(@Query("ids") String ids);

    @PUT("me/tracks")
    Observable<Result> addToMySavedTracksRx(@Query("ids") String ids);

    @GET("me/tracks")
    Call<Pager<SavedTrack>> getMySavedTracks();

    @GET("me/tracks")
    Observable<Pager<SavedTrack>> getMySavedTracksRx();

    @GET("me/tracks")
    Call<Pager<SavedTrack>> getMySavedTracks(@QueryMap Map<String, Object> options);

    @GET("me/tracks")
    Observable<Pager<SavedTrack>> getMySavedTracksRx(@QueryMap Map<String, Object> options);

    @DELETE("me/tracks")
    Call<Result> removeFromMySavedTracks(@Query("ids") String ids);

    @DELETE("me/tracks")
    Observable<Result> removeFromMySavedTracksRx(@Query("ids") String ids);

    @GET("me/tracks/contains")
    Call<Boolean[]> containsMySavedTracks(@Query("ids") String ids);

    @GET("me/tracks/contains")
    Observable<Boolean[]> containsMySavedTracksRx(@Query("ids") String ids);

    @PUT("me/albums")
    Call<Result> addToMySavedAlbums(@Query("ids") String ids);

    @PUT("me/albums")
    Observable<Result> addToMySavedAlbumsRx(@Query("ids") String ids);

    @GET("me/albums")
    Call<Pager<SavedAlbum>> getMySavedAlbums();

    @GET("me/albums")
    Observable<Pager<SavedAlbum>> getMySavedAlbumsRx();

    @GET("me/albums")
    Call<Pager<SavedAlbum>> getMySavedAlbums(@QueryMap Map<String, Object> options);

    @GET("me/albums")
    Observable<Pager<SavedAlbum>> getMySavedAlbumsRx(@QueryMap Map<String, Object> options);

    @DELETE("me/albums")
    Call<Result> removeFromMySavedAlbums(@Query("ids") String ids);

    @DELETE("me/albums")
    Observable<Result> removeFromMySavedAlbumsRx(@Query("ids") String ids);

    @GET("me/albums/contains")
    Call<Boolean[]> containsMySavedAlbums(@Query("ids") String ids);

    @GET("me/albums/contains")
    Observable<Boolean[]> containsMySavedAlbumsRx(@Query("ids") String ids);

    /*************
     * Personalization *
     *************/

    @GET("me/top/artists")
    Call<Pager<Artist>> getTopArtists();

    @GET("me/top/artists")
    Observable<Pager<Artist>> getTopArtistsRx();

    @GET("me/top/artists")
    Call<Pager<Artist>> getTopArtists(@QueryMap Map<String, Object> options);

    @GET("me/top/artists")
    Observable<Pager<Artist>> getTopArtistsRx(@QueryMap Map<String, Object> options);

    @GET("me/top/tracks")
    Call<Pager<Track>> getTopTracks();

    @GET("me/top/tracks")
    Observable<Pager<Track>> getTopTracksRx();

    @GET("me/top/tracks")
    Call<Pager<Track>> getTopTracks(@QueryMap Map<String, Object> options);

    @GET("me/top/tracks")
    Observable<Pager<Track>> getTopTracksRx(@QueryMap Map<String, Object> options);

    @GET("me/player/recently-played")
    Call<Pager<PlayHistory>> getRecentlyPlayedTracks();

    @GET("me/player/recently-played")
    Observable<Pager<PlayHistory>> getRecentlyPlayedTracksRx();

    @GET("me/player/recently-played")
    Call<Pager<PlayHistory>> getRecentlyPlayedTracks(@QueryMap Map<String, Object> options);

    @GET("me/player/recently-played")
    Observable<Pager<PlayHistory>> getRecentlyPlayedTracksRx(@QueryMap Map<String, Object> options);

    /*************
     * Player *
     *************/

    @GET("me/player/devices")
    Call<Payload> getUsersAvailableDevices();

    @GET("me/player/devices")
    Observable<Payload> getUsersAvailableDevicesRx();

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentPlayback();

    @GET("me/player")
    Observable<CurrentlyPlayingContext> getCurrentPlaybackRx();

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentPlayback(@QueryMap Map<String, Object> options);

    @GET("me/player")
    Observable<CurrentlyPlayingContext> getCurrentPlaybackRx(@QueryMap Map<String, Object> options);

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentlyPlayingTrack();

    @GET("me/player")
    Observable<CurrentlyPlayingContext> getCurrentlyPlayingTrackRx();

    @GET("me/player")
    Call<CurrentlyPlayingContext> getCurrentlyPlayingTrack(@QueryMap Map<String, Object> options);

    @GET("me/player")
    Observable<CurrentlyPlayingContext> getCurrentlyPlayingTrackRx(@QueryMap Map<String, Object> options);

    @PUT("me/player")
    Call<Result> transferUserPlayback(@Body Map<String, Object> body);

    @PUT("me/player")
    Observable<Result> transferUserPlaybackRx(@Body Map<String, Object> body);

    @PUT("me/player/play")
    Call<Result> startUserPlayback(@Body TracksToRemove body);

    @PUT("me/player/play")
    Observable<Result> startUserPlaybackRx(@Body TracksToRemove body);

    @PUT("me/player/play")
    Call<Result> startUserPlayback(@Query("device_id") String device_id, @Body Map<String, Object> body);

    @PUT("me/player/play")
    Observable<Result> startUserPlaybackRx(@Query("device_id") String device_id, @Body Map<String, Object> body);

    @PUT("me/player/play")
    Call<Result> resumeUserPlayback();

    @PUT("me/player/play")
    Observable<Result> resumeUserPlaybackRx();

    @PUT("me/player/play")
    Call<Result> resumeUserPlayback(@Query("device_id") String device_id);

    @PUT("me/player/play")
    Observable<Result> resumeUserPlaybackRx(@Query("device_id") String device_id);

    @PUT("me/player/pause")
    Call<Result> pauseUserPlayback();

    @PUT("me/player/pause")
    Observable<Result> pauseUserPlaybackRx();

    @PUT("me/player/pause")
    Call<Result> pauseUserPlayback(@Query("device_id") String device_id);

    @PUT("me/player/pause")
    Observable<Result> pauseUserPlaybackRx(@Query("device_id") String device_id);

    @PUT("me/player/next")
    Call<Result> skipToTheNextTrack();

    @PUT("me/player/next")
    Observable<Result> skipToTheNextTrackRx();

    @PUT("me/player/next")
    Call<Result> skipToTheNextTrack(@Query("device_id") String device_id);

    @PUT("me/player/next")
    Observable<Result> skipToTheNextTrackRx(@Query("device_id") String device_id);

    @PUT("me/player/previous")
    Call<Result> skipToThePreviousTrack();

    @PUT("me/player/previous")
    Observable<Result> skipToThePreviousTrackRx();

    @PUT("me/player/previous")
    Call<Result> skipToThePreviousTrack(@Query("device_id") String device_id);

    @PUT("me/player/previous")
    Observable<Result> skipToThePreviousTrackRx(@Query("device_id") String device_id);

    @PUT("me/player/seek")
    Call<Result> seekToPositionInCurrentTrack(@Query("position_ms") int position_ms);

    @PUT("me/player/seek")
    Observable<Result> seekToPositionInCurrentTrackRx(@Query("position_ms") int position_ms);

    @PUT("me/player/seek")
    Call<Result> seekToPositionInCurrentTrack(@Query("position_ms") int position_ms, @Query("device_id") String device_id);

    @PUT("me/player/seek")
    Observable<Result> seekToPositionInCurrentTrackRx(@Query("position_ms") int position_ms, @Query("device_id") String device_id);

    @PUT("me/player/repeat")
    Call<Result> setRepeatMode(@Query("state") String state);

    @PUT("me/player/repeat")
    Observable<Result> setRepeatModeRx(@Query("state") String state);

    @PUT("me/player/repeat")
    Call<Result> setRepeatMode(@Query("state") String state, @Query("device_id") String device_id);

    @PUT("me/player/repeat")
    Observable<Result> setRepeatModeRx(@Query("state") String state, @Query("device_id") String device_id);

    @PUT("me/player/volume")
    Call<Result> setVolume(@Query("volume_percent") int volume_percent);

    @PUT("me/player/volume")
    Observable<Result> setVolumeRx(@Query("volume_percent") int volume_percent);

    @PUT("me/player/volume")
    Call<Result> setVolume(@Query("volume_percent") int volume_percent, @Query("device_id") String device_id);

    @PUT("me/player/volume")
    Observable<Result> setVolumeRx(@Query("volume_percent") int volume_percent, @Query("device_id") String device_id);

    @PUT("me/player/shuffle")
    Call<Result> toggleShuffle(@Query("state") boolean state);

    @PUT("me/player/shuffle")
    Observable<Result> toggleShuffleRx(@Query("state") boolean state);

    @PUT("me/player/shuffle")
    Call<Result> toggleShuffle(@Query("state") boolean state, @Query("device_id") String device_id);

    @PUT("me/player/shuffle")
    Observable<Result> toggleShuffleRx(@Query("state") boolean state, @Query("device_id") String device_id);

    /*************
     * Playlists *
     *************/

    @GET("users/{id}/playlists")
    Call<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId, @QueryMap Map<String, Object> options);

    @GET("users/{id}/playlists")
    Observable<Pager<PlaylistSimple>> getPlaylistsRx(@Path("id") String userId, @QueryMap Map<String, Object> options);

    @GET("users/{id}/playlists")
    Call<Pager<PlaylistSimple>> getPlaylists(@Path("id") String userId);

    @GET("users/{id}/playlists")
    Observable<Pager<PlaylistSimple>> getPlaylistsRx(@Path("id") String userId);

    @GET("me/playlists")
    Call<Pager<PlaylistSimple>> getMyPlaylists();

    @GET("me/playlists")
    Observable<Pager<PlaylistSimple>> getMyPlaylistsRx();

    @GET("me/playlists")
    Call<Pager<Playlist>> getMyPlaylists(@QueryMap Map<String, Object> options);

    @GET("me/playlists")
    Observable<Pager<Playlist>> getMyPlaylistsRx(@QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}")
    Observable<Playlist> getPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}")
    Observable<Playlist> getPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<Pager<PlaylistTrack>> getPlaylistTracksRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> options);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Pager<PlaylistTrack>> getPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @GET("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<Pager<PlaylistTrack>> getPlaylistTracksRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId);

    @POST("users/{user_id}/playlists")
    Call<Playlist> createPlaylist(@Path("user_id") String userId, @Body Map<String, Object> options);

    @POST("users/{user_id}/playlists")
    Observable<Playlist> createPlaylistRx(@Path("user_id") String userId, @Body Map<String, Object> options);

    @PUT("users/{user_id}/playlists/{playlist_id}")
    Call<Result> changePlaylistDetails(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @PUT("users/{user_id}/playlists/{playlist_id}")
    Observable<Result> changePlaylistDetailsRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @POST("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> queryParameters, @Body Map<String, Object> body);

    @POST("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<SnapshotId> addTracksToPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @QueryMap Map<String, Object> queryParameters, @Body Map<String, Object> body);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<SnapshotId> removeTracksFromPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemove tracksToRemove);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition);

    @DELETE("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<SnapshotId> removeTracksFromPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveWithPosition tracksToRemoveWithPosition);

    @DELETE("/users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksFromPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveByPosition tracksToRemoveByPosition);

    @DELETE("/users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<SnapshotId> removeTracksFromPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body TracksToRemoveByPosition tracksToRemoveByPosition);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<SnapshotId> reorderPlaylistTracks(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<SnapshotId> reorderPlaylistTracksRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Body Map<String, Object> body);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    Call<Result> replaceTracksInPlaylist(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Body Object body);

    @PUT("users/{user_id}/playlists/{playlist_id}/tracks")
    Observable<Result> replaceTracksInPlaylistRx(@Path("user_id") String userId, @Path("playlist_id") String playlistId, @Query("uris") String trackUris, @Body Object body);

    /************
     * Profiles *
     ************/

    @GET("me")
    Call<UserPrivate> getMe();

    @GET("me")
    Observable<UserPrivate> getMeRx();

    @GET("users/{id}")
    Call<UserPublic> getUser(@Path("id") String userId);

    @GET("users/{id}")
    Observable<UserPublic> getUserRx(@Path("id") String userId);

    /*************
     * Search *
     *************/

    @GET("search?type=track")
    Call<TracksPager> searchTracks(@Query("q") String q);

    @GET("search?type=track")
    Observable<TracksPager> searchTracksRx(@Query("q") String q);

    @GET("search?type=track")
    Call<TracksPager> searchTracks(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=track")
    Observable<TracksPager> searchTracksRx(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=artist")
    Call<ArtistsPager> searchArtists(@Query("q") String q);

    @GET("search?type=artist")
    Observable<ArtistsPager> searchArtistsRx(@Query("q") String q);

    @GET("search?type=artist")
    Call<ArtistsPager> searchArtists(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=artist")
    Observable<ArtistsPager> searchArtistsRx(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=album")
    Call<AlbumsPager> searchAlbums(@Query("q") String q);

    @GET("search?type=album")
    Observable<AlbumsPager> searchAlbumsRx(@Query("q") String q);

    @GET("search?type=album")
    Call<AlbumsPager> searchAlbums(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=album")
    Observable<AlbumsPager> searchAlbumsRx(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=playlist")
    Call<PlaylistsPager> searchPlaylists(@Query("q") String q);

    @GET("search?type=playlist")
    Observable<PlaylistsPager> searchPlaylistsRx(@Query("q") String q);

    @GET("search?type=playlist")
    Call<PlaylistsPager> searchPlaylists(@Query("q") String q, @QueryMap Map<String, Object> options);

    @GET("search?type=playlist")
    Observable<PlaylistsPager> searchPlaylistsRx(@Query("q") String q, @QueryMap Map<String, Object> options);

    /*************
     * Tracks *
     *************/

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String trackId);

    @GET("tracks/{id}")
    Observable<Track> getTrackRx(@Path("id") String trackId);

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String trackId, @QueryMap Map<String, Object> options);

    @GET("tracks/{id}")
    Observable<Track> getTrackRx(@Path("id") String trackId, @QueryMap Map<String, Object> options);

    @GET("tracks")
    Call<Tracks> getTracks(@Query("ids") String trackIds);

    @GET("tracks")
    Observable<Tracks> getTracksRx(@Query("ids") String trackIds);

    @GET("tracks")
    Call<Tracks> getTracks(@Query("ids") String trackIds, @QueryMap Map<String, Object> options);

    @GET("tracks")
    Observable<Tracks> getTracksRx(@Query("ids") String trackIds, @QueryMap Map<String, Object> options);

    @GET("audio-features")
    Call<AudioFeaturesTrack> getTracksAudioFeatures(@Query("ids") String ids);

    @GET("audio-features")
    Observable<AudioFeaturesTrack> getTracksAudioFeaturesRx(@Query("ids") String ids);

    @GET("audio-features/{id}")
    Call<AudioFeaturesTrack> getTrackAudioFeatures(@Path("id") String id);

    @GET("audio-features/{id}")
    Observable<AudioFeaturesTrack> getTrackAudioFeaturesRx(@Path("id") String id);

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

        private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

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

        public Builder withRxJava2CallAdapterFactory(RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
            this.rxJava2CallAdapterFactory = rxJava2CallAdapterFactory;
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

            if (rxJava2CallAdapterFactory == null) {
                rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
            }

            Retrofit restAdapter = retrofitBuilder
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .client(okHttpClientBuilder.build())
                    .build();

            return restAdapter.create(SpotifyService.class);
        }
    }
}

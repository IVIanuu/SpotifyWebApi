package com.ivianuu.spotifywebapi.sample;

import android.app.Application;
import android.support.annotation.NonNull;

import com.ivianuu.spotifyextensions.RateLimitingInterceptor;
import com.ivianuu.spotifyextensions.auth.SpotifyAuthenticator;
import com.ivianuu.spotifyextensions.auth.SpotifyTokenInterceptor;
import com.ivianuu.spotifyextensions.auth.TokenHandler;
import com.ivianuu.spotifywebapi.SpotifyAuthenticationService;
import com.ivianuu.spotifywebapi.SpotifyService;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.ivianuu.spotifyextensions.auth.Scopes.PLAYLIST_MODIFY_PRIVATE;
import static com.ivianuu.spotifyextensions.auth.Scopes.PLAYLIST_MODIFY_PUBLIC;
import static com.ivianuu.spotifyextensions.auth.Scopes.PLAYLIST_READ_COLLABORATIVE;
import static com.ivianuu.spotifyextensions.auth.Scopes.PLAYLIST_READ_PRIVATE;
import static com.ivianuu.spotifyextensions.auth.Scopes.USER_FOLLOW_READ;
import static com.ivianuu.spotifyextensions.auth.Scopes.USER_MODIFY_PLAYBACK_STATE;
import static com.ivianuu.spotifyextensions.auth.Scopes.USER_READ_EMAIL;
import static com.ivianuu.spotifyextensions.auth.Scopes.USER_READ_PLAYBACK_STATE;
import static com.ivianuu.spotifyextensions.auth.Scopes.USER_TOP_READ;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public class App extends Application implements TokenHandler {

    public static final String CLIENT_ID = "2eadca5243d5475b9f8003088b2a170b";//"paste your client id here";
    public static final String CLIENT_SECRET = "7f87178a2df048d8883192c2e001ac9e";//"paste your client secret here";
    public static final String REDIRECT_URI = "rxspotifyplayer://callback";//"paste your redirect uri here";
    public static final String[] SCOPES = new String[]{USER_MODIFY_PLAYBACK_STATE, USER_READ_PLAYBACK_STATE, USER_FOLLOW_READ, USER_TOP_READ, USER_READ_EMAIL, PLAYLIST_MODIFY_PRIVATE, PLAYLIST_MODIFY_PUBLIC, PLAYLIST_READ_PRIVATE, PLAYLIST_READ_COLLABORATIVE};

    private SpotifyService spotifyService;
    private SpotifyAuthenticationService spotifyAuthenticationService;

    private String accessToken;
    private String refreshToken;

    @Override
    public void onCreate() {
        super.onCreate();

        spotifyService = provideSpotifyService();
        spotifyAuthenticationService = provideAuthenticationService();

    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(@NonNull String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public void setRefreshToken(@NonNull String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public SpotifyService getSpotifyService() {
        return spotifyService;
    }

    public SpotifyAuthenticationService getSpotifyAuthenticationService() {
        return spotifyAuthenticationService;
    }

    private SpotifyService provideSpotifyService() {
        return new SpotifyService.Builder()
                .withOkHttpClient(provideHttpClient())
                .withRxJava2CallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    private OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .authenticator(provideAuthenticator())
                .addInterceptor(provideTokenInterceptor())
                .addInterceptor(provideRateLimitingInterceptor())
                .build();
    }

    private SpotifyAuthenticator provideAuthenticator() {
        return new SpotifyAuthenticator.Builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .spotifyAuthenticationService(provideAuthenticationService())
                .tokenHandler(this)
                .build();
    }

    private SpotifyTokenInterceptor provideTokenInterceptor() {
        return new SpotifyTokenInterceptor(this);
    }

    private SpotifyAuthenticationService provideAuthenticationService() {
        return new SpotifyAuthenticationService.Builder()
                .withRxJava2CallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    private RateLimitingInterceptor provideRateLimitingInterceptor() {
        return new RateLimitingInterceptor.Builder().maxRetries(5).build();
    }
}

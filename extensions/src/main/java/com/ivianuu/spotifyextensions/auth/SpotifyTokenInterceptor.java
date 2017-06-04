package com.ivianuu.spotifyextensions.auth;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Add's the access token to every request
 */

public final class SpotifyTokenInterceptor implements Interceptor {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private TokenHandler tokenHandler;

    /**
     * @param tokenHandler the token handler
     */
    public SpotifyTokenInterceptor(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;

        newRequest = request.newBuilder()
                .addHeader(TOKEN_HEADER, TOKEN_PREFIX + tokenHandler.getAccessToken())
                .build();

        Log.d("testtt", newRequest.url().toString());

        return chain.proceed(newRequest);
    }
}

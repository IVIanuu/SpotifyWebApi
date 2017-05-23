package com.ivianuu.spotifywebapi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Manuel Wrage (IVIanuu)
 */

class AuthInterceptor implements Interceptor {

    private String mAccessToken;

    AuthInterceptor(String accessToken) {
        mAccessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;

        newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();

        return chain.proceed(newRequest);
    }
}

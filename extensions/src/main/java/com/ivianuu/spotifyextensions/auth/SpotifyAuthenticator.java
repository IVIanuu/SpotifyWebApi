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

package com.ivianuu.spotifyextensions.auth;

import android.support.annotation.NonNull;

import com.ivianuu.spotifywebapi.SpotifyAuthenticationService;
import com.ivianuu.spotifywebapi.model.AccessToken;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * A class which automatically refreshes the token
 * You need to pass this in your http client builder
 */
public final class SpotifyAuthenticator implements Authenticator {

    private String clientId;
    private String clientSecret;
    private int maxRetries;
    private SpotifyAuthenticationService spotifyAuthenticationService;
    private TokenHandler tokenHandler;

    private SpotifyAuthenticator(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.maxRetries = builder.maxRetries;
        this.spotifyAuthenticationService = builder.spotifyAuthenticationService;
        this.tokenHandler = builder.tokenHandler;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (responseCount(response) >= maxRetries) {
            return null; // give up.
        }

        // get new tokens
        AccessToken tokenResponse
                = spotifyAuthenticationService.refreshAccessTokenBody(
                "refresh_token", tokenHandler.getRefreshToken(), clientId, clientSecret).toObservable().blockingFirst();
        if (tokenResponse != null) {
            // save token
            tokenHandler.setAccessToken(tokenResponse.accessToken);
        }

        return response.request().newBuilder()
                .header("Authorization", "Bearer " + tokenHandler.getAccessToken())
                .build();
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    public static class Builder {

        private String clientId;
        private String clientSecret;
        private int maxRetries = 3; // default value
        private SpotifyAuthenticationService spotifyAuthenticationService;
        private TokenHandler tokenHandler;

        /**
         * @param clientId the client id
         */
        public Builder clientId(@NonNull String clientId) {
            this.clientId = clientId;
            return this;
        }

        /**
         * @param clientSecret the client client secret
         */
        public Builder clientSecret(@NonNull String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        /**
         * @param maxRetries max retries if fetching a new token failed
         */
        public Builder maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        /**
         * @param spotifyAuthenticationService a spotify authentication service instance
         */
        public Builder spotifyAuthenticationService(@NonNull SpotifyAuthenticationService spotifyAuthenticationService) {
            this.spotifyAuthenticationService = spotifyAuthenticationService;
            return this;
        }

        /**
         * @param tokenHandler a token handler
         */
        public Builder tokenHandler(@NonNull TokenHandler tokenHandler) {
            this.tokenHandler = tokenHandler;
            return this;
        }

        public SpotifyAuthenticator build() {
            if (tokenHandler == null) {
                throw new IllegalStateException("missing token handler");
            } else if (spotifyAuthenticationService == null) {
                throw new IllegalStateException("missing spotify authentication service");
            } else if (clientId == null) {
                throw new IllegalStateException("missing client id");
            } else if (clientSecret == null) {
                throw new IllegalStateException("missing client secret");
            }

            return new SpotifyAuthenticator(this);
        }

    }

}

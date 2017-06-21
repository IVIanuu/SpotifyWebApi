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

package com.ivianuu.spotifywebapi;

import com.ivianuu.spotifywebapi.model.AccessToken;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Authentication service to get or refresh access tokens
 */
public interface SpotifyAuthenticationService {

    String BASE_URL = "https://accounts.spotify.com/api/";

    /**
     * Get's an access token
     *
     * @param grantType should always be "authorization_code"
     * @param code the code
     * @param redirectUri your redirect uri
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<AccessToken> getAccessTokenBody(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    /**
     * Get's an access token
     *
     * @param grantType should always be "authorization_code"
     * @param code the code
     * @param redirectUri your redirect uri
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<Response<AccessToken>> getAccessTokenResponse(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    /**
     * Get's an access token
     *
     * @param grantType should always be "authorization_code"
     * @param code the code
     * @param redirectUri your redirect uri
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<ResponseBody> getAccessTokenResponseBody(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    /**
     * Get's an access token
     *
     * @param grantType should always be "authorization_code"
     * @param code the code
     * @param redirectUri your redirect uri
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<Result<AccessToken>> getAccessTokenResult(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);


    /**
     * Get an refreshed token
     *
     * @param grantType should always be "refresh_token"
     * @param refreshToken the refresh token
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<AccessToken> refreshAccessTokenBody(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    /**
     * Get an refreshed token
     *
     * @param grantType should always be "refresh_token"
     * @param refreshToken the refresh token
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<Response<AccessToken>> refreshAccessTokenResponse(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    /**
     * Get an refreshed token
     *
     * @param grantType should always be "refresh_token"
     * @param refreshToken the refresh token
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<ResponseBody> refreshAccessTokenResponseBody(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    /**
     * Get an refreshed token
     *
     * @param grantType should always be "refresh_token"
     * @param refreshToken the refresh token
     * @param clientId your client id
     * @param clientSecret your client secret
     * @return The access token wrapped in a object
     * @see <a href="https://developer.spotify.com/web-api/authorization-guide/#authorization-code-flow">Authorization code flow</a>
     */
    @FormUrlEncoded
    @POST("token")
    Single<Result<AccessToken>> refreshAccessTokenResult(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

    class Builder {

        private Retrofit.Builder retrofitBuilder;

        private OkHttpClient.Builder okHttpClientBuilder;

        private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

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

        public SpotifyAuthenticationService build() {
            if (retrofitBuilder == null) {
                retrofitBuilder = new Retrofit.Builder();
            }

            if (okHttpClientBuilder == null) {
                okHttpClientBuilder = new OkHttpClient.Builder();
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

            return restAdapter.create(SpotifyAuthenticationService.class);
        }
    }
}

package com.ivianuu.spotifywebapi;

import com.ivianuu.spotifywebapi.model.AccessToken;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
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
     * Get's access token
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
    Call<AccessToken> getAccessToken(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

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
    Observable<AccessToken> getAccessTokenRx(@Field("grant_type") String grantType, @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

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
    Call<AccessToken> refreshAccessToken(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

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
    Observable<AccessToken> refreshAccessTokenRx(@Field("grant_type") String grantType, @Field("refresh_token") String refreshToken, @Field("client_id") String clientId, @Field("client_secret") String clientSecret);

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

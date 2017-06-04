package com.ivianuu.spotifywebapi.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ivianuu.spotifywebapi.SpotifyAuthenticationService;
import com.ivianuu.spotifywebapi.SpotifyService;
import com.ivianuu.spotifywebapi.model.AccessToken;
import com.ivianuu.spotifywebapi.model.UserPrivate;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MainActivity extends AppCompatActivity {

    String CLIENT_ID = "paste your client id here";
    String CLIENT_SECRET = "paste your client secret here";
    String REDIRECT_URI = "paste your redirect uri here";
    String[] SCOPES = new String[]{"user-read-private"};

    private static final int AUTH_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AuthenticationRequest request = getAuthenticationRequest();
        AuthenticationClient.openLoginActivity(this, AUTH_REQUEST_CODE, request);
    }

    private AuthenticationRequest getAuthenticationRequest() {
        return new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setShowDialog(false)
                .setScopes(SCOPES)
                .build();
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTH_REQUEST_CODE) {
            // handle response
            final AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                case CODE:
                    // we have our code

                    // build authentication service
                    SpotifyAuthenticationService spotifyAuthenticationService = new SpotifyAuthenticationService.Builder()
                            .build();

                    // get access token
                    spotifyAuthenticationService.getAccessTokenRx(
                            "authorization_code", response.getCode(), REDIRECT_URI, CLIENT_ID, CLIENT_SECRET)
                            .switchMap(new Function<AccessToken, ObservableSource<UserPrivate>>() {
                                @Override
                                public ObservableSource<UserPrivate> apply(AccessToken accessToken) throws Exception {
                                    // build spotify service
                                    SpotifyService spotifyService = new SpotifyService.Builder()
                                            .withAccessToken(accessToken.accessToken)
                                            .withRxJava2CallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                                            .build();

                                    // get user
                                    return spotifyService.getMeRx();
                                }
                            })
                            .subscribe(new Consumer<UserPrivate>() {
                                @Override
                                public void accept(UserPrivate userPrivate) throws Exception {
                                    // successfully received user
                                    Log.d("webapi", userPrivate.email);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    // handle error
                                    Log.e("webapi", throwable.getMessage());
                                }
                            });

                    break;
                default:
                    // handle other cases
                    break;
            }
        }
    }
}

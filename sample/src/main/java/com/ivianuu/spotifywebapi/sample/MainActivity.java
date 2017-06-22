package com.ivianuu.spotifywebapi.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivianuu.spotifywebapi.SpotifyAuthenticationService;
import com.ivianuu.spotifywebapi.model.AccessToken;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import io.reactivex.functions.Consumer;

import static com.ivianuu.spotifywebapi.sample.App.CLIENT_ID;
import static com.ivianuu.spotifywebapi.sample.App.CLIENT_SECRET;
import static com.ivianuu.spotifywebapi.sample.App.REDIRECT_URI;
import static com.ivianuu.spotifywebapi.sample.App.SCOPES;

public class MainActivity extends AppCompatActivity {

    private static final int AUTH_REQUEST_CODE = 100;

    private SpotifyAuthenticationService spotifyAuthenticationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spotifyAuthenticationService = ((App) getApplicationContext()).getSpotifyAuthenticationService();

        final AuthenticationRequest request = getAuthenticationRequest();
        AuthenticationClient.openLoginActivity(this, AUTH_REQUEST_CODE, request);
    }

    private AuthenticationRequest getAuthenticationRequest() {
        return new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.CODE, REDIRECT_URI)
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
                    // get access token
                    spotifyAuthenticationService.getAccessTokenBody(
                            "authorization_code", response.getCode(), REDIRECT_URI, CLIENT_ID, CLIENT_SECRET)
                            .subscribe(new Consumer<AccessToken>() {
                                @Override
                                public void accept(AccessToken accessToken) throws Exception {
                                    ((App) getApplicationContext()).setAccessToken(accessToken.access_token());
                                    ((App) getApplicationContext()).setRefreshToken(accessToken.refresh_token());

                                    startActivity(new Intent(MainActivity.this, PaginationActivity.class));
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                    // handle error
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

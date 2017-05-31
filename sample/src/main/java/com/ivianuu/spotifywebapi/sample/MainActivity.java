package com.ivianuu.spotifywebapi.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ivianuu.spotifyauth.AuthenticationClient;
import com.ivianuu.spotifyauth.AuthenticationRequest;
import com.ivianuu.spotifyauth.AuthenticationResponse;
import com.ivianuu.spotifywebapi.SpotifyService;
import com.ivianuu.spotifywebapi.model.UserPrivate;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MainActivity extends AppCompatActivity {

    String CLIENT_ID = "154dba9755364b4e904e5650e9a1134a";
    String REDIRECT_URI = "spotifysample://callback";

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
                .addScopes(new String[]{"user-read-private"})
                .build();
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTH_REQUEST_CODE) {
            final AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            String accessToken = response.getAccessToken();

            SpotifyService spotifyService = new SpotifyService.Builder()
                    .withAccessToken(accessToken)
                    .withRxJava2CallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();

            spotifyService.getMe()
                    .subscribe(new Consumer<UserPrivate>() {
                        @Override
                        public void accept(UserPrivate userPrivate) throws Exception {
                            Log.d("webapi", userPrivate.email);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("webapi", throwable.getMessage());
                        }
                    });
        }
    }
}

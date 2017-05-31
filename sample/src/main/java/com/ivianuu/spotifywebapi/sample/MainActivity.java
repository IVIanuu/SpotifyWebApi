package com.ivianuu.spotifywebapi.sample;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ivianuu.dynamiccalladapter.AsyncCall;
import com.ivianuu.dynamiccalladapter.DynamicCall;
import com.ivianuu.dynamiccalladapter.Result;
import com.ivianuu.spotifyauth.AuthenticationClient;
import com.ivianuu.spotifyauth.AuthenticationRequest;
import com.ivianuu.spotifyauth.AuthenticationResponse;
import com.ivianuu.spotifywebapi.SpotifyService;
import com.ivianuu.spotifywebapi.model.TracksPager;
import com.ivianuu.spotifywebapi.model.UserPrivate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends LifecycleActivity {

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
                    .build();

            spotifyService.getMe()
                    .asV2Single()
                    .body()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<UserPrivate>() {
                        @Override
                        public void accept(@NonNull UserPrivate userPrivate) throws Exception {
                            Toast.makeText(MainActivity.this, userPrivate.display_name, Toast.LENGTH_SHORT).show();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });
        }
    }
}

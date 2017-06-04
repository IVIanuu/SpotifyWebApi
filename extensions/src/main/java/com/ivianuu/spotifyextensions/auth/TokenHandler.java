package com.ivianuu.spotifyextensions.auth;

import android.support.annotation.NonNull;

/**
 * The implementation should persist or hold the tokens
 */

public interface TokenHandler {
    String getAccessToken();
    void setAccessToken(@NonNull String accessToken);
    String getRefreshToken();
    void setRefreshToken(@NonNull String refreshToken);
}
package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public class AuthenticationResponse implements Parcelable {

    public static final Creator<AuthenticationResponse> CREATOR = new Creator<AuthenticationResponse>() {
        @Override
        public AuthenticationResponse createFromParcel(Parcel in) {
            return new AuthenticationResponse(in);
        }

        @Override
        public AuthenticationResponse[] newArray(int size) {
            return new AuthenticationResponse[size];
        }
    };

    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("expires_in")
    public int expiresIn;
    @SerializedName("refresh_token")
    public String refreshToken;

    public AuthenticationResponse() {

    }

    protected AuthenticationResponse(Parcel in) {
        this.accessToken = in.readString();
        this.tokenType = in.readString();
        this.expiresIn = in.readInt();
        this.refreshToken = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
        dest.writeString(tokenType);
        dest.writeInt(expiresIn);
        dest.writeString(refreshToken);
    }
}

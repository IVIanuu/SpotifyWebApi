package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;

public class UserPrivate extends UserPublic {
    public static final Creator<UserPrivate> CREATOR = new Creator<UserPrivate>() {
        public UserPrivate createFromParcel(Parcel source) {
            return new UserPrivate(source);
        }

        public UserPrivate[] newArray(int size) {
            return new UserPrivate[size];
        }
    };
    public String birthdate;
    public String country;
    public String email;
    public String product;

    public UserPrivate() {
    }

    protected UserPrivate(Parcel in) {
        super(in);
        this.birthdate = in.readString();
        this.country = in.readString();
        this.email = in.readString();
        this.product = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.birthdate);
        dest.writeString(this.country);
        dest.writeString(this.email);
        dest.writeString(this.product);
    }
}

package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Copyright implements Parcelable {
    public static final Parcelable.Creator<Copyright> CREATOR = new Parcelable.Creator<Copyright>() {
        public Copyright createFromParcel(Parcel source) {
            return new Copyright(source);
        }

        public Copyright[] newArray(int size) {
            return new Copyright[size];
        }
    };
    public String text;
    public String type;

    public Copyright() {
    }

    protected Copyright(Parcel in) {
        this.text = in.readString();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.type);
    }
}

package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Result() {
    }

    protected Result(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

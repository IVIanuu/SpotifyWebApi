package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ErrorDetails implements Parcelable {
    public static final Parcelable.Creator<ErrorDetails> CREATOR = new Parcelable.Creator<ErrorDetails>() {
        public ErrorDetails createFromParcel(Parcel source) {
            return new ErrorDetails(source);
        }

        public ErrorDetails[] newArray(int size) {
            return new ErrorDetails[size];
        }
    };
    public int status;
    public String message;

    public ErrorDetails() {
    }

    protected ErrorDetails(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.message);
    }
}

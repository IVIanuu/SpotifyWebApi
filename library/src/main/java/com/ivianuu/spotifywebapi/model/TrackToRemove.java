package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrackToRemove implements Parcelable {

    public static final Parcelable.Creator<TrackToRemove> CREATOR = new Parcelable.Creator<TrackToRemove>() {
        public TrackToRemove createFromParcel(Parcel source) {
            return new TrackToRemove(source);
        }

        public TrackToRemove[] newArray(int size) {
            return new TrackToRemove[size];
        }
    };
    public String uri;

    public TrackToRemove() {
    }

    protected TrackToRemove(Parcel in) {
        this.uri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uri);
    }
}

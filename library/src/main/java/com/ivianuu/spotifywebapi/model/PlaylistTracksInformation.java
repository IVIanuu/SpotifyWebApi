package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaylistTracksInformation implements Parcelable {
    public static final Parcelable.Creator<PlaylistTracksInformation> CREATOR = new Parcelable.Creator<PlaylistTracksInformation>() {
        public PlaylistTracksInformation createFromParcel(Parcel source) {
            return new PlaylistTracksInformation(source);
        }

        public PlaylistTracksInformation[] newArray(int size) {
            return new PlaylistTracksInformation[size];
        }
    };
    public String href;
    public int total;

    public PlaylistTracksInformation() {
    }

    protected PlaylistTracksInformation(Parcel in) {
        this.href = in.readString();
        this.total = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
        dest.writeInt(this.total);
    }
}

package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaylistsPager implements Parcelable {
    public static final Parcelable.Creator<PlaylistsPager> CREATOR = new Parcelable.Creator<PlaylistsPager>() {
        public PlaylistsPager createFromParcel(Parcel source) {
            return new PlaylistsPager(source);
        }

        public PlaylistsPager[] newArray(int size) {
            return new PlaylistsPager[size];
        }
    };
    public Pager<PlaylistSimple> playlists;

    public PlaylistsPager() {
    }

    protected PlaylistsPager(Parcel in) {
        this.playlists = in.readParcelable(Pager.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.playlists, 0);
    }
}

package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Albums implements Parcelable {

    public static final Parcelable.Creator<Albums> CREATOR = new Parcelable.Creator<Albums>() {
        public Albums createFromParcel(Parcel source) {
            return new Albums(source);
        }

        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };
    public List<Album> albums;

    public Albums() {
    }

    protected Albums(Parcel in) {
        this.albums = in.createTypedArrayList(Album.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(albums);
    }
}

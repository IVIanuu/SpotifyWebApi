/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ivianuu.spotifywebapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Map;

public class Album extends AlbumSimple implements Parcelable {

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
    public List<ArtistSimple> artists;
    public List<Copyright> copyrights;
    public Map<String, String> external_ids;
    public List<String> genres;
    public Integer popularity;
    public String release_date;
    public String release_date_precision;
    public Pager<TrackSimple> tracks;

    public Album() {
    }

    protected Album(Parcel in) {
        super(in);
        this.artists = in.createTypedArrayList(ArtistSimple.CREATOR);
        this.copyrights = in.createTypedArrayList(Copyright.CREATOR);
        this.external_ids = in.readHashMap(ClassLoader.getSystemClassLoader());
        this.genres = in.createStringArrayList();
        this.popularity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.release_date = in.readString();
        this.release_date_precision = in.readString();
        this.tracks = in.readParcelable(Pager.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(artists);
        dest.writeTypedList(copyrights);
        dest.writeMap(this.external_ids);
        dest.writeStringList(this.genres);
        dest.writeValue(this.popularity);
        dest.writeString(this.release_date);
        dest.writeString(this.release_date_precision);
        dest.writeParcelable(this.tracks, flags);
    }
}
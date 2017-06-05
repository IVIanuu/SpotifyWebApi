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

import java.util.Map;

public class Track extends TrackSimple {

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        public Track createFromParcel(Parcel source) {
            return new Track(source);
        }

        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
    public AlbumSimple album;
    public Map<String, String> external_ids;
    public Integer popularity;

    public Track() {
    }

    protected Track(Parcel in) {
        super(in);
        this.album = in.readParcelable(AlbumSimple.class.getClassLoader());
        this.external_ids = in.readHashMap(Map.class.getClassLoader());
        this.popularity = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.album, 0);
        dest.writeMap(this.external_ids);
        dest.writeValue(this.popularity);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + this.id.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Track)) return false;

        Track other = (Track) o;
        return this.id != null && other.id != null && this.id.equals(other.id);
    }
}
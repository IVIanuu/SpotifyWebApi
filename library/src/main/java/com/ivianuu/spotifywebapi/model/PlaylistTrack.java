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

public class PlaylistTrack implements Parcelable {
    public static final Parcelable.Creator<PlaylistTrack> CREATOR = new Parcelable.Creator<PlaylistTrack>() {
        public PlaylistTrack createFromParcel(Parcel source) {
            return new PlaylistTrack(source);
        }

        public PlaylistTrack[] newArray(int size) {
            return new PlaylistTrack[size];
        }
    };
    public String added_at;
    public UserPublic added_by;
    public Track track;
    public Boolean is_local;

    public PlaylistTrack() {
    }

    protected PlaylistTrack(Parcel in) {
        this.added_at = in.readString();
        this.added_by = in.readParcelable(UserPublic.class.getClassLoader());
        this.track = in.readParcelable(Track.class.getClassLoader());
        this.is_local = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.added_at);
        dest.writeParcelable(this.added_by, flags);
        dest.writeParcelable(this.track, 0);
        dest.writeValue(this.is_local);
    }
}

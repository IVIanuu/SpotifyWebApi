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

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

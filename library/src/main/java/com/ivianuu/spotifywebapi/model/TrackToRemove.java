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

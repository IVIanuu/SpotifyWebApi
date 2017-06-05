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

import java.util.ArrayList;
import java.util.List;

public class TracksToRemoveWithPosition implements Parcelable {
    public static final Parcelable.Creator<TracksToRemoveWithPosition> CREATOR = new Parcelable.Creator<TracksToRemoveWithPosition>() {
        public TracksToRemoveWithPosition createFromParcel(Parcel source) {
            return new TracksToRemoveWithPosition(source);
        }

        public TracksToRemoveWithPosition[] newArray(int size) {
            return new TracksToRemoveWithPosition[size];
        }
    };
    public List<TrackToRemoveWithPosition> tracks;

    public TracksToRemoveWithPosition() {
    }

    protected TracksToRemoveWithPosition(Parcel in) {
        this.tracks = new ArrayList<TrackToRemoveWithPosition>();
        in.readList(this.tracks, List.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.tracks);
    }
}

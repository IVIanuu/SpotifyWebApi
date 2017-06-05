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

public class TrackToRemoveWithPosition implements Parcelable {
    public static final Parcelable.Creator<TrackToRemoveWithPosition> CREATOR = new Parcelable.Creator<TrackToRemoveWithPosition>() {
        public TrackToRemoveWithPosition createFromParcel(Parcel source) {
            return new TrackToRemoveWithPosition(source);
        }

        public TrackToRemoveWithPosition[] newArray(int size) {
            return new TrackToRemoveWithPosition[size];
        }
    };
    public String uri;
    public List<Integer> positions;

    public TrackToRemoveWithPosition() {
    }

    protected TrackToRemoveWithPosition(Parcel in) {
        this.uri = in.readString();
        this.positions = new ArrayList<Integer>();
        in.readList(this.positions, List.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uri);
        dest.writeList(this.positions);
    }
}

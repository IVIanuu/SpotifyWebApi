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

public class Recommendations implements Parcelable {

    public static final Creator<Recommendations> CREATOR = new Creator<Recommendations>() {
        @Override
        public Recommendations createFromParcel(Parcel in) {
            return new Recommendations(in);
        }

        @Override
        public Recommendations[] newArray(int size) {
            return new Recommendations[size];
        }
    };
    public List<Seed> seeds;
    public List<Track> tracks;

    public Recommendations() {
    }

    protected Recommendations(Parcel in) {
        seeds = in.createTypedArrayList(Seed.CREATOR);
        tracks = in.createTypedArrayList(Track.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(seeds);
        dest.writeTypedList(tracks);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

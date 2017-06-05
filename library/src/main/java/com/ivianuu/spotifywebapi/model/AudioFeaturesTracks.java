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

public class AudioFeaturesTracks implements Parcelable {

    public static final Creator<AudioFeaturesTracks> CREATOR = new Creator<AudioFeaturesTracks>() {
        @Override
        public AudioFeaturesTracks createFromParcel(Parcel in) {
            return new AudioFeaturesTracks(in);
        }

        @Override
        public AudioFeaturesTracks[] newArray(int size) {
            return new AudioFeaturesTracks[size];
        }
    };
    public List<AudioFeaturesTrack> audio_features;

    public AudioFeaturesTracks() {
    }

    protected AudioFeaturesTracks(Parcel in) {
        audio_features = in.createTypedArrayList(AudioFeaturesTrack.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(audio_features);
    }
}

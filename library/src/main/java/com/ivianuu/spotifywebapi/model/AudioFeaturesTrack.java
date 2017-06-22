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

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class AudioFeaturesTrack implements Parcelable {

    public abstract float acousticness();
    public abstract String analysis_url();
    public abstract float danceability();
    public abstract int duration_ms();
    public abstract float energy();
    public abstract String id();
    public abstract float instrumentalness();
    public abstract int key();
    public abstract float liveness();
    public abstract float loudness();
    public abstract int mode();
    public abstract float speechiness();
    public abstract float tempo();
    public abstract int time_signature();
    public abstract String track_href();
    public abstract String type();
    public abstract String uri();
    public abstract float valence();

    public static Builder builder() {
        return new AutoValue_AudioFeaturesTrack.Builder();
    }

    public static TypeAdapter<AudioFeaturesTrack> typeAdapter(Gson gson) {
        return new AutoValue_AudioFeaturesTrack.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder acousticness(float __);
        public abstract Builder analysis_url(String __);
        public abstract Builder danceability(float __);
        public abstract Builder duration_ms(int __);
        public abstract Builder energy(float __);
        public abstract Builder id(String __);
        public abstract Builder instrumentalness(float __);
        public abstract Builder key(int __);
        public abstract Builder liveness(float __);
        public abstract Builder loudness(float __);
        public abstract Builder mode(int __);
        public abstract Builder speechiness(float __);
        public abstract Builder tempo(float __);
        public abstract Builder time_signature(int __);
        public abstract Builder track_href(String __);
        public abstract Builder type(String __);
        public abstract Builder uri(String __);
        public abstract Builder valence(float __);
        public abstract AudioFeaturesTrack build();
    }
}

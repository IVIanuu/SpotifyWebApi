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
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Track implements Parcelable {

    public abstract AlbumSimple album();
    public abstract List<ArtistSimple> artists();
    @Nullable public abstract List<String> available_markets();
    public abstract int disc_number();
    public abstract long duration_ms();
    public abstract Boolean explicit();
    public abstract Map<String, String> external_ids();
    public abstract Map<String, String> external_urls();
    public abstract String href();
    public abstract String id();
    @Nullable public abstract Boolean is_playable();
    @Nullable public abstract LinkedTrack linked_from();
    public abstract String name();
    public abstract Integer popularity();
    @Nullable public abstract String preview_url();
    public abstract int track_number();
    public abstract String type();
    public abstract String uri();

    public static Builder builder() {
        return new AutoValue_Track.Builder();
    }

    public static TypeAdapter<Track> typeAdapter(Gson gson) {
        return new AutoValue_Track.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder album(AlbumSimple __);
        public abstract Builder artists(List<ArtistSimple> __);
        public abstract Builder available_markets(List<String> __);
        public abstract Builder disc_number(int __);
        public abstract Builder duration_ms(long __);
        public abstract Builder explicit(Boolean __);
        public abstract Builder external_ids(Map<String, String> __);
        public abstract Builder external_urls(Map<String, String> __);
        public abstract Builder href(String __);
        public abstract Builder id(String __);
        public abstract Builder is_playable(Boolean __);
        public abstract Builder linked_from(LinkedTrack __);
        public abstract Builder name(String __);
        public abstract Builder popularity(Integer __);
        public abstract Builder preview_url(String __);
        public abstract Builder track_number(int __);
        public abstract Builder type(String __);
        public abstract Builder uri(String __);
        public abstract Track build();
    }
}
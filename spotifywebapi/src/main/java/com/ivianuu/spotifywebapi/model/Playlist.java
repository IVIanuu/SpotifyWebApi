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

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Playlist {

    public abstract Boolean collaborative();
    @Nullable public abstract String description();
    public abstract Map<String, String> external_urls();
    public abstract Followers followers();
    public abstract String href();
    public abstract String id();
    public abstract List<Image> images();
    public abstract String name();
    public abstract UserPublic owner();
    @Nullable public abstract Boolean is_public();
    public abstract String snapshot_id();
    public abstract Pager<PlaylistTrack> tracks();
    public abstract String type();
    public abstract String uri();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Playlist.Builder();
    }

    public static TypeAdapter<Playlist> typeAdapter(Gson gson) {
        return new AutoValue_Playlist.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder collaborative(Boolean __);
        public abstract Builder description(String __);
        public abstract Builder external_urls(Map<String, String> __);
        public abstract Builder followers(Followers __);
        public abstract Builder href(String __);
        public abstract Builder id(String __);
        public abstract Builder images(List<Image> __);
        public abstract Builder name(String __);
        public abstract Builder owner(UserPublic __);
        public abstract Builder is_public(Boolean __);
        public abstract Builder snapshot_id(String __);
        public abstract Builder tracks(Pager<PlaylistTrack> __);
        public abstract Builder type(String __);
        public abstract Builder uri(String __);
        public abstract Playlist build();
    }
}
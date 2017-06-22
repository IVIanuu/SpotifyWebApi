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
public abstract class PlaylistSimple {

    public abstract Boolean collaborative();
    public abstract Map<String, String> external_urls();
    public abstract String href();
    public abstract String id();
    public abstract List<Image> images();
    public abstract String name();
    public abstract UserPublic owner();
    @Nullable public abstract Boolean is_public();
    public abstract String snapshot_id();
    public abstract PlaylistTracksInformation tracks();
    public abstract String type();
    public abstract String uri();

    public static Builder builder() {
        return new AutoValue_PlaylistSimple.Builder();
    }

    public static TypeAdapter<PlaylistSimple> typeAdapter(Gson gson) {
        return new AutoValue_PlaylistSimple.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder collaborative(Boolean collaborative);

        public abstract Builder external_urls(Map<String, String> external_urls);

        public abstract Builder href(String href);

        public abstract Builder id(String id);

        public abstract Builder images(List<Image> images);

        public abstract Builder name(String name);

        public abstract Builder owner(UserPublic owner);

        public abstract Builder is_public(Boolean is_public);

        public abstract Builder snapshot_id(String snapshot_id);

        public abstract Builder tracks(PlaylistTracksInformation tracks);

        public abstract Builder type(String type);

        public abstract Builder uri(String uri);

        public abstract PlaylistSimple build();
    }
}

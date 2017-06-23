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

@AutoValue
public abstract class PlaylistTrack implements Parcelable {

    @Nullable public abstract String added_at();
    @Nullable public abstract UserPublic added_by();
    public abstract Boolean is_local();
    public abstract Track track();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_PlaylistTrack.Builder();
    }

    public static TypeAdapter<PlaylistTrack> typeAdapter(Gson gson) {
        return new AutoValue_PlaylistTrack.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder added_at(String __);
        public abstract Builder added_by(UserPublic __);
        public abstract Builder is_local(Boolean __);
        public abstract Builder track(Track __);
        public abstract PlaylistTrack build();
    }
}

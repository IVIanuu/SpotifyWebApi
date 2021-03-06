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
public abstract class ArtistsCursorPager implements Parcelable {

    public abstract CursorPager<Artist> artists();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_ArtistsCursorPager.Builder();
    }

    public static TypeAdapter<ArtistsCursorPager> typeAdapter(Gson gson) {
        return new AutoValue_ArtistsCursorPager.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder artists(CursorPager<Artist> __);
        public abstract ArtistsCursorPager build();
    }
}

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
public abstract class Seed implements Parcelable {

    public abstract int afterFilteringSize();
    public abstract int afterRelinkingSize();
    @Nullable public abstract String href();
    public abstract String id();
    public abstract int initialPoolSize();
    public abstract String type();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Seed.Builder();
    }

    public static TypeAdapter<Seed> typeAdapter(Gson gson) {
        return new AutoValue_Seed.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder afterFilteringSize(int __);
        public abstract Builder afterRelinkingSize(int __);
        public abstract Builder href(String __);
        public abstract Builder id(String __);
        public abstract Builder initialPoolSize(int __);
        public abstract Builder type(String __);
        public abstract Seed build();
    }
}

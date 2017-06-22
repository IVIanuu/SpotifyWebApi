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
public abstract class Image implements Parcelable {

    @Nullable public abstract Integer height();
    public abstract String url();
    @Nullable public abstract Integer width();

    public static Builder builder() {
        return new AutoValue_Image.Builder();
    }

    public static TypeAdapter<Image> typeAdapter(Gson gson) {
        return new AutoValue_Image.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder height(Integer __);
        public abstract Builder url(String __);
        public abstract Builder width(Integer __);
        public abstract Image build();
    }
}

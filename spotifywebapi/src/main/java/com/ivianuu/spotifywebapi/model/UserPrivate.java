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
public abstract class UserPrivate  {

    @Nullable public abstract String birthdate();
    @Nullable public abstract String country();
    @Nullable public abstract String email();
    @Nullable public abstract String display_name();
    public abstract Map<String, String> external_urls();
    public abstract Followers followers();
    public abstract String href();
    public abstract String id();
    public abstract List<Image> images();
    @Nullable public abstract String product();
    public abstract String type();
    public abstract String uri();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_UserPrivate.Builder();
    }

    public static TypeAdapter<UserPrivate> typeAdapter(Gson gson) {
        return new AutoValue_UserPrivate.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder birthdate(String __);
        public abstract Builder country(String __);
        public abstract Builder email(String __);
        public abstract Builder display_name(String __);
        public abstract Builder external_urls(Map<String, String> __);
        public abstract Builder followers(Followers __);
        public abstract Builder href(String __);
        public abstract Builder id(String __);
        public abstract Builder images(List<Image> __);
        public abstract Builder product(String __);
        public abstract Builder type(String __);
        public abstract Builder uri(String __);
        public abstract UserPrivate build();
    }
}

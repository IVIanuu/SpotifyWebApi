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
import com.google.gson.annotations.SerializedName;

import java.util.Map;

@AutoValue
public abstract class Context implements Parcelable {

    @SerializedName("href") public abstract String href();
    @SerializedName("external_urls") public abstract Map<String, String> external_urls();
    @SerializedName("type") public abstract String type();
    @SerializedName("uri") public abstract String uri();

    public static Builder builder() {
        return new AutoValue_Context.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder type(String __);
        public abstract Builder href(String __);
        public abstract Builder external_urls(Map<String, String> __);
        public abstract Builder uri(String __);
        public abstract Context build();
    }
}
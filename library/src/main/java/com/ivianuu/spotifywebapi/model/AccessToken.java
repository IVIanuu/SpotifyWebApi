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
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class AccessToken implements Parcelable {

    @SerializedName("access_token") public abstract String accessToken();
    @SerializedName("expires_in") public abstract int expiresIn();
    @SerializedName("refresh_token") public abstract String refreshToken();
    @SerializedName("scope") public abstract String scope();
    @SerializedName("token_type") public abstract String tokenType();

    public static Builder builder() {
        return new AutoValue_AccessToken.Builder();
    }

    public static TypeAdapter<AccessToken> typeAdapter(Gson gson) {
        return new AutoValue_AccessToken.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder accessToken(String __);
        public abstract Builder expiresIn(int __);
        public abstract Builder refreshToken(String __);
        public abstract Builder scope(String __);
        public abstract Builder tokenType(String __);
        public abstract AccessToken build();
    }
}

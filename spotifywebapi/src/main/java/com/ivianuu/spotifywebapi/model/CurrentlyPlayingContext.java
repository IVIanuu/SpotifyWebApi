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
public abstract class CurrentlyPlayingContext implements Parcelable {

    @Nullable public abstract Context context();
    public abstract Device device();
    public abstract boolean is_playing();
    public abstract Track item();
    public abstract int progress_ms();
    public abstract String repeat_state();
    public abstract boolean shuffle_state();
    public abstract long timestamp();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_CurrentlyPlayingContext.Builder();
    }

    public static TypeAdapter<CurrentlyPlayingContext> typeAdapter(Gson gson) {
        return new AutoValue_CurrentlyPlayingContext.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder device(Device __);
        public abstract Builder repeat_state(String __);
        public abstract Builder shuffle_state(boolean __);
        public abstract Builder context(Context __);
        public abstract Builder timestamp(long __);
        public abstract Builder progress_ms(int __);
        public abstract Builder is_playing(boolean __);
        public abstract Builder item(Track __);
        public abstract CurrentlyPlayingContext build();
    }
}

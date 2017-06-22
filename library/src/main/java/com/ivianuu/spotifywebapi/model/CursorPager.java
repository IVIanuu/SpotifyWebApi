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
import com.google.gson.reflect.TypeToken;
import com.ivianuu.spotifywebapi.ListTypeAdapter;
import com.ryanharter.auto.value.parcel.ParcelAdapter;

import java.util.List;

@AutoValue
public abstract class CursorPager<T> implements Parcelable {

    public abstract String href();
    @ParcelAdapter(ListTypeAdapter.class)
    public abstract List<T> items();
    public abstract int limit();
    @Nullable public abstract String next();
    public abstract Cursor cursors();
    public abstract int total();

    public abstract Builder<T> toBuilder();

    public static Builder builder() {
        return new AutoValue_CursorPager.Builder();
    }

    public static <T> TypeAdapter<CursorPager<T>> typeAdapter(Gson gson, TypeToken<? extends CursorPager<T>> typeToken) {
        return new AutoValue_CursorPager.GsonTypeAdapter<>(gson, typeToken);
    }

    @AutoValue.Builder
    public static abstract class Builder<T> {
        public abstract Builder<T> href(String __);
        public abstract Builder<T> items(List<T> __);
        public abstract Builder<T> limit(int __);
        public abstract Builder<T> next(String __);
        public abstract Builder<T> cursors(Cursor __);
        public abstract Builder<T> total(int __);
        public abstract CursorPager<T> build();
    }
}

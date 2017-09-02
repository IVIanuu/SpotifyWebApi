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

package com.ivianuu.spotifywebapi;

import android.os.Parcel;

import com.ryanharter.auto.value.parcel.TypeAdapter;

import java.util.List;

/**
 * List type adapter
 */
public class ListTypeAdapter implements TypeAdapter<List<?>> {

    @Override
    public List<?> fromParcel(Parcel in) {
        return in.readArrayList(List.class.getClassLoader());
    }

    @Override
    public void toParcel(List<?> value, Parcel dest) {
        dest.writeList(value);
    }
}

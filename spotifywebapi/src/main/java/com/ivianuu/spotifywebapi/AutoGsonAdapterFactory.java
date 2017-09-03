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

import android.support.annotation.NonNull;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Auto Gson adapter factory
 */
@GsonTypeAdapterFactory
abstract class AutoGsonAdapterFactory implements TypeAdapterFactory {

    /**
     * Returns a new auto gson adapter factory
     */
    @NonNull
    static AutoGsonAdapterFactory create() {
        return new AutoValueGson_AutoGsonAdapterFactory();
    }
}

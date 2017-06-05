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

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Context implements Parcelable {

    public static final Creator<Context> CREATOR = new Creator<Context>() {
        @Override
        public Context createFromParcel(Parcel in) {
            return new Context(in);
        }

        @Override
        public Context[] newArray(int size) {
            return new Context[size];
        }
    };
    public String type;
    public String href;
    public Map<String, String> external_urls;
    public String uri;

    public Context() {
    }

    protected Context(Parcel in) {
        type = in.readString();
        href = in.readString();
        external_urls = in.readHashMap(Map.class.getClassLoader());
        uri = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(href);
        dest.writeMap(external_urls);
        dest.writeString(uri);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
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

public class Seed implements Parcelable {

    public static final Creator<Seed> CREATOR = new Creator<Seed>() {
        @Override
        public Seed createFromParcel(Parcel in) {
            return new Seed(in);
        }

        @Override
        public Seed[] newArray(int size) {
            return new Seed[size];
        }
    };
    public int afterFilteringSize;
    public int afterRelinkingSize;
    public String href;
    public String id;
    public int initialPoolSize;
    public String type;

    public Seed() {
    }

    protected Seed(Parcel in) {
        afterFilteringSize = in.readInt();
        afterRelinkingSize = in.readInt();
        href = in.readString();
        id = in.readString();
        initialPoolSize = in.readInt();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(afterFilteringSize);
        dest.writeInt(afterRelinkingSize);
        dest.writeString(href);
        dest.writeString(id);
        dest.writeInt(initialPoolSize);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

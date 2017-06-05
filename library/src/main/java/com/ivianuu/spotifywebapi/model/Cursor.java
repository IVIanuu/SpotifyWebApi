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

public class Cursor implements Parcelable {
    public static final Creator<Cursor> CREATOR = new Creator<Cursor>() {
        public Cursor createFromParcel(Parcel source) {
            return new Cursor(source);
        }

        public Cursor[] newArray(int size) {
            return new Cursor[size];
        }
    };
    public String after;

    public Cursor() {
    }

    protected Cursor(Parcel in) {
        this.after = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.after);
    }
}

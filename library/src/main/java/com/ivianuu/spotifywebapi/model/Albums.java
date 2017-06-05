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

import java.util.List;

public class Albums implements Parcelable {

    public static final Parcelable.Creator<Albums> CREATOR = new Parcelable.Creator<Albums>() {
        public Albums createFromParcel(Parcel source) {
            return new Albums(source);
        }

        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };
    public List<Album> albums;

    public Albums() {
    }

    protected Albums(Parcel in) {
        this.albums = in.createTypedArrayList(Album.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(albums);
    }
}

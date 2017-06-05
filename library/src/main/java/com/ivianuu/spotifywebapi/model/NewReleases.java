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

public class NewReleases implements Parcelable {
    public static final Parcelable.Creator<NewReleases> CREATOR = new Parcelable.Creator<NewReleases>() {
        public NewReleases createFromParcel(Parcel source) {
            return new NewReleases(source);
        }

        public NewReleases[] newArray(int size) {
            return new NewReleases[size];
        }
    };
    public Pager<AlbumSimple> albums;

    public NewReleases() {
    }

    protected NewReleases(Parcel in) {
        this.albums = in.readParcelable(Pager.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.albums, 0);
    }
}

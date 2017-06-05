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

public class SavedAlbum implements Parcelable {
    public static final Parcelable.Creator<SavedAlbum> CREATOR = new Parcelable.Creator<SavedAlbum>() {
        public SavedAlbum createFromParcel(Parcel source) {
            return new SavedAlbum(source);
        }

        public SavedAlbum[] newArray(int size) {
            return new SavedAlbum[size];
        }
    };
    public String added_at;
    public Album album;

    public SavedAlbum() {
    }

    protected SavedAlbum(Parcel in) {
        this.added_at = in.readString();
        this.album = in.readParcelable(Album.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.added_at);
        dest.writeParcelable(this.album, 0);
    }
}
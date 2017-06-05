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

import java.util.List;

public class Artist extends ArtistSimple {

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
    public Followers followers;
    public List<String> genres;
    public List<Image> images;
    public Integer popularity;

    public Artist() {
    }

    protected Artist(Parcel in) {
        super(in);
        this.followers = in.readParcelable(Followers.class.getClassLoader());
        this.genres = in.createStringArrayList();
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.popularity = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.followers, flags);
        dest.writeStringList(this.genres);
        dest.writeTypedList(images);
        dest.writeValue(this.popularity);
    }
}
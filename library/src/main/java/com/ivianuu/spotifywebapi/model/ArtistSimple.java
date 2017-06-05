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

public class ArtistSimple implements Parcelable {

    public static final Creator<ArtistSimple> CREATOR = new Creator<ArtistSimple>() {
        public ArtistSimple createFromParcel(Parcel source) {
            return new ArtistSimple(source);
        }

        public ArtistSimple[] newArray(int size) {
            return new ArtistSimple[size];
        }
    };
    public Map<String, String> external_urls;
    public String href;
    public String id;
    public String name;
    public String type;
    public String uri;


    public ArtistSimple() {
    }

    protected ArtistSimple(Parcel in) {
        this.external_urls = in.readHashMap(Map.class.getClassLoader());
        this.href = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.uri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(this.external_urls);
        dest.writeString(this.href);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.uri);
    }
}

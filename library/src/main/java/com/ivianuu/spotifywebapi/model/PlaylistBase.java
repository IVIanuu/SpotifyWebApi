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

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public abstract class PlaylistBase implements Parcelable {
    public Boolean collaborative;
    public Map<String, String> external_urls;
    public String href;
    public String id;
    public List<Image> images;
    public String name;
    public UserPublic owner;
    @SerializedName("public")
    public Boolean is_public;
    public String snapshot_id;
    public String type;
    public String uri;

    protected PlaylistBase() {
    }

    protected PlaylistBase(Parcel in) {
        this.collaborative = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.external_urls = in.readHashMap(Map.class.getClassLoader());
        this.href = (String) in.readValue(String.class.getClassLoader());
        this.id = (String) in.readValue(String.class.getClassLoader());
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.name = (String) in.readValue(String.class.getClassLoader());
        this.owner = in.readParcelable(UserPublic.class.getClassLoader());
        this.is_public = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.snapshot_id = (String) in.readValue(String.class.getClassLoader());
        this.type = (String) in.readValue(String.class.getClassLoader());
        this.uri = (String) in.readValue(String.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.collaborative);
        dest.writeMap(this.external_urls);
        dest.writeValue(this.href);
        dest.writeValue(this.id);
        dest.writeTypedList(this.images);
        dest.writeValue(this.name);
        dest.writeParcelable(owner, flags);
        dest.writeValue(is_public);
        dest.writeValue(snapshot_id);
        dest.writeValue(type);
        dest.writeValue(uri);
    }
}

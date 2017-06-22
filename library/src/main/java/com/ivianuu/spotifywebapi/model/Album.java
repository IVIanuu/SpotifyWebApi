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

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;
import java.util.Map;

@AutoValue
public abstract class Album implements Parcelable {

    public abstract String album_type();
    public abstract List<ArtistSimple> artists();
    public abstract List<String> available_markets();
    public abstract List<Copyright> copyrights();
    public abstract List<String> genres();
    public abstract Map<String, String> external_ids();
    public abstract Map<String, String> external_urls();
    public abstract String href();
    public abstract String id();
    public abstract List<Image> images();
    public abstract String name();
    public abstract Integer popularity();
    public abstract String release_date();
    public abstract String release_date_precision();
    public abstract Pager<TrackSimple> tracks();
    public abstract String type();
    public abstract String uri();

    public static Builder builder() {
        return new AutoValue_Album.Builder();
    }

    public static TypeAdapter<Album> albumTypeAdapter(Gson gson) {
        return new AutoValue_Album.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder album_type(String __);
        public abstract Builder artists(List<ArtistSimple> __);
        public abstract Builder available_markets(List<String> __);
        public abstract Builder copyrights(List<Copyright> __);
        public abstract Builder genres(List<String> __);
        public abstract Builder external_ids(Map<String, String> __);
        public abstract Builder external_urls(Map<String, String> __);
        public abstract Builder href(String __);
        public abstract Builder id(String __);
        public abstract Builder images(List<Image> __);
        public abstract Builder name(String name);
        public abstract Builder popularity(Integer __);
        public abstract Builder release_date(String __);
        public abstract Builder release_date_precision(String __);
        public abstract Builder tracks(Pager<TrackSimple> __);
        public abstract Builder type(String __);
        public abstract Builder uri(String __);
        public abstract Album build();
    }


}
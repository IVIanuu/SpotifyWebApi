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

public class PlayHistory implements Parcelable {

    public static final Creator<PlayHistory> CREATOR = new Creator<PlayHistory>() {
        @Override
        public PlayHistory createFromParcel(Parcel in) {
            return new PlayHistory(in);
        }

        @Override
        public PlayHistory[] newArray(int size) {
            return new PlayHistory[size];
        }
    };
    public TrackSimple track;
    public String played_at;
    public Context context;

    public PlayHistory() {
    }

    protected PlayHistory(Parcel in) {
        track = in.readParcelable(TrackSimple.class.getClassLoader());
        played_at = in.readString();
        context = in.readParcelable(Context.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(track, 0);
        dest.writeString(played_at);
        dest.writeParcelable(context, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
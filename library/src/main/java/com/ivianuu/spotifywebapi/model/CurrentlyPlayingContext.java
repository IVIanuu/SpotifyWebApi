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

public class CurrentlyPlayingContext implements Parcelable {

    public static final Parcelable.Creator<CurrentlyPlayingContext> CREATOR = new Parcelable.Creator<CurrentlyPlayingContext>() {
        public CurrentlyPlayingContext createFromParcel(Parcel source) {
            return new CurrentlyPlayingContext(source);
        }

        public CurrentlyPlayingContext[] newArray(int size) {
            return new CurrentlyPlayingContext[size];
        }
    };
    public Device device;
    public String repeat_state;
    public boolean shuffle_state;
    public Context context;
    public long timestamp;
    public int progress_ms;
    public boolean is_playing;
    public Track item;

    public CurrentlyPlayingContext() {
    }

    protected CurrentlyPlayingContext(Parcel in) {
        this.device = in.readParcelable(Device.class.getClassLoader());
        this.repeat_state = in.readString();
        this.shuffle_state = in.readInt() == 1;
        this.context = in.readParcelable(Context.class.getClassLoader());
        this.timestamp = in.readLong();
        this.progress_ms = in.readInt();
        this.is_playing = in.readInt() == 1;
        this.item = in.readParcelable(Track.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(device, 0);
        dest.writeString(repeat_state);
        dest.writeInt(shuffle_state ? 1 : 0);
        dest.writeParcelable(context, 0);
        dest.writeLong(timestamp);
        dest.writeInt(progress_ms);
        dest.writeInt(is_playing ? 1 : 0);
        dest.writeParcelable(item, 0);
    }
}

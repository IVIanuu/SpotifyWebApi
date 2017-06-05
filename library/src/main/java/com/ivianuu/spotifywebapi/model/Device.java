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

public class Device implements Parcelable {

    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
        public Device createFromParcel(Parcel source) {
            return new Device(source);
        }

        public Device[] newArray(int size) {
            return new Device[size];
        }
    };
    public String id;
    public boolean is_active;
    public boolean is_restricted;
    public String name;
    public String type;
    public int volume_percent;

    public Device() {
    }

    protected Device(Parcel in) {
        this.id = in.readString();
        this.is_active = in.readInt() == 1;
        this.is_restricted = in.readInt() == 1;
        this.name = in.readString();
        this.type = in.readString();
        this.volume_percent = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(is_active ? 1 : 0);
        dest.writeInt(is_restricted ? 1 : 0);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(volume_percent);
    }
}

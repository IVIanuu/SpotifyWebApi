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

public class Copyright implements Parcelable {
    public static final Parcelable.Creator<Copyright> CREATOR = new Parcelable.Creator<Copyright>() {
        public Copyright createFromParcel(Parcel source) {
            return new Copyright(source);
        }

        public Copyright[] newArray(int size) {
            return new Copyright[size];
        }
    };
    public String text;
    public String type;

    public Copyright() {
    }

    protected Copyright(Parcel in) {
        this.text = in.readString();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.type);
    }
}

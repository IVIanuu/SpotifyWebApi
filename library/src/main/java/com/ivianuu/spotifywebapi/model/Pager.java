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

import java.util.List;

public class Pager<T> implements Parcelable {
    public static final Parcelable.Creator<Pager> CREATOR = new Parcelable.Creator<Pager>() {
        public Pager createFromParcel(Parcel source) {
            return new Pager(source);
        }

        public Pager[] newArray(int size) {
            return new Pager[size];
        }
    };
    public String href;
    public List<T> items;
    public int limit;
    public String next;
    public int offset;
    public String previous;
    public int total;

    public Pager() {
    }

    protected Pager(Parcel in) {
        this.href = in.readString();
        this.items = in.readArrayList(Pager.class.getClassLoader());
        this.limit = in.readInt();
        this.next = in.readString();
        this.offset = in.readInt();
        this.previous = in.readString();
        this.total = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
        dest.writeList(items);
        dest.writeInt(limit);
        dest.writeString(next);
        dest.writeInt(offset);
        dest.writeString(previous);
        dest.writeInt(total);
    }

}

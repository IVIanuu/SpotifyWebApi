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

import java.util.ArrayList;
import java.util.List;

public class CursorPager<T> implements Parcelable {
    public static final Creator<CursorPager> CREATOR = new Creator<CursorPager>() {
        public CursorPager createFromParcel(Parcel source) {
            return new CursorPager(source);
        }

        public CursorPager[] newArray(int size) {
            return new CursorPager[size];
        }
    };
    public String href;
    public List<T> items;
    public int limit;
    public String next;
    public Cursor cursors;
    public int total;

    public CursorPager() {
    }

    protected CursorPager(Parcel in) {
        this.href = in.readString();
        this.items = in.readArrayList(ArrayList.class.getClassLoader());
        this.limit = in.readInt();
        this.next = in.readString();
        this.cursors = in.readParcelable(Cursor.class.getClassLoader());
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
        dest.writeParcelable(this.cursors, flags);
        dest.writeInt(total);
    }

}

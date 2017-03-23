/*
 * Copyright [2016] [Ashish Paul]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zerogravity.myapplication.data.common.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ashish on 03-04-2016.
 */
public class League implements Parcelable {
    public String id;
    public String name = "";
    public String iconUrlSmall = "";
    public String iconUrlTiny = "";
    public String iconUrlMedium = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrlSmall() {
        return iconUrlSmall;
    }

    public void setIconUrlSmall(String iconUrlSmall) {
        this.iconUrlSmall = iconUrlSmall;
    }

    public String getIconUrlTiny() {
        return iconUrlTiny;
    }

    public void setIconUrlTiny(String iconUrlTiny) {
        this.iconUrlTiny = iconUrlTiny;
    }

    public String getIconUrlMedium() {
        return iconUrlMedium;
    }

    public void setIconUrlMedium(String iconUrlMedium) {
        this.iconUrlMedium = iconUrlMedium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.iconUrlSmall);
        dest.writeString(this.iconUrlTiny);
        dest.writeString(this.iconUrlMedium);
    }

    public League() {
    }

    protected League(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.iconUrlSmall = in.readString();
        this.iconUrlTiny = in.readString();
        this.iconUrlMedium = in.readString();
    }

    public static final Parcelable.Creator<League> CREATOR = new Parcelable.Creator<League>() {
        @Override
        public League createFromParcel(Parcel source) {
            return new League(source);
        }

        @Override
        public League[] newArray(int size) {
            return new League[size];
        }
    };
}

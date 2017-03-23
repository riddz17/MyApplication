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
public class Location implements Parcelable {
    public String id = "";
    public String name = "";
    public boolean isCountry;
    public String countryCode = "";

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

    public boolean isCountry() {
        return isCountry;
    }

    public void setIsCountry(boolean isCountry) {
        this.isCountry = isCountry;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.isCountry ? (byte) 1 : (byte) 0);
        dest.writeString(this.countryCode);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.isCountry = in.readByte() != 0;
        this.countryCode = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}

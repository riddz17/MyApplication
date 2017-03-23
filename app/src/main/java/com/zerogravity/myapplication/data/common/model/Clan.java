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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by ashish on 23-03-2016.
 */
public class Clan implements Parcelable {
    public String tag = "";
    public String name = "";
    public String type = "";
    public String description = "";
    public String badgeUrlSmall = "";
    public String badgeUrlMedium = "";
    public String badgeUrLarge = "";
    public String warFrequency = "";
    public String clanLevel = "";
    public String warWins = "";
    public String warWinStreak = "";
    public String clanPoints = "";
    public String requiredTrophies = "";
    public String members = "";
    public Location location;



    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        String clanDescEncoded;
        try {
            clanDescEncoded = URLDecoder.decode(description, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return description;
        }
        return clanDescEncoded;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBadgeUrlSmall() {
        return badgeUrlSmall;
    }

    public void setBadgeUrlSmall(String badgeUrlSmall) {
        this.badgeUrlSmall = badgeUrlSmall;
    }

    public String getBadgeUrlMedium() {
        return badgeUrlMedium;
    }

    public void setBadgeUrlMedium(String badgeUrlMedium) {
        this.badgeUrlMedium = badgeUrlMedium;
    }

    public String getBadgeUrLarge() {
        return badgeUrLarge;
    }

    public void setBadgeUrLarge(String badgeUrLarge) {
        this.badgeUrLarge = badgeUrLarge;
    }

    public String getWarFrequency() {
        return warFrequency;
    }

    public void setWarFrequency(String warFrequency) {
        this.warFrequency = warFrequency;
    }

    public String getClanLevel() {
        return clanLevel;
    }

    public void setClanLevel(String clanLevel) {
        this.clanLevel = clanLevel;
    }

    public String getWarWins() {
        return warWins;
    }

    public void setWarWins(String warWins) {
        this.warWins = warWins;
    }

    public String getWarWinStreak() {
        return warWinStreak;
    }

    public void setWarWinStreak(String warWinStreak) {
        this.warWinStreak = warWinStreak;
    }

    public String getClanPoints() {
        return clanPoints;
    }

    public void setClanPoints(String clanPoints) {
        this.clanPoints = clanPoints;
    }

    public String getRequiredTrophies() {
        return requiredTrophies;
    }

    public void setRequiredTrophies(String requiredTrophies) {
        this.requiredTrophies = requiredTrophies;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.description);
        dest.writeString(this.badgeUrlSmall);
        dest.writeString(this.badgeUrlMedium);
        dest.writeString(this.badgeUrLarge);
        dest.writeString(this.warFrequency);
        dest.writeString(this.clanLevel);
        dest.writeString(this.warWins);
        dest.writeString(this.warWinStreak);
        dest.writeString(this.clanPoints);
        dest.writeString(this.requiredTrophies);
        dest.writeString(this.members);
        dest.writeParcelable(this.location, flags);
    }

    public Clan() {
    }

    protected Clan(Parcel in) {
        this.tag = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.description = in.readString();
        this.badgeUrlSmall = in.readString();
        this.badgeUrlMedium = in.readString();
        this.badgeUrLarge = in.readString();
        this.warFrequency = in.readString();
        this.clanLevel = in.readString();
        this.warWins = in.readString();
        this.warWinStreak = in.readString();
        this.clanPoints = in.readString();
        this.requiredTrophies = in.readString();
        this.members = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Parcelable.Creator<Clan> CREATOR = new Parcelable.Creator<Clan>() {
        @Override
        public Clan createFromParcel(Parcel source) {
            return new Clan(source);
        }

        @Override
        public Clan[] newArray(int size) {
            return new Clan[size];
        }
    };
}

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
 * Created by ashish on 23-03-2016.
 */
public class Player implements Parcelable {

    public String tag = "";
    public String name = "";
    public String role = "";
    public String expLevel;
    public String trophies;
    public String clanRank;
    public String previousClanRank;
    public String donations;
    public String donationsReceived;
    public Clan clan;
    public League league;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(String expLevel) {
        this.expLevel = expLevel;
    }

    public String getTrophies() {
        return trophies;
    }

    public void setTrophies(String trophies) {
        this.trophies = trophies;
    }

    public String getClanRank() {
        return clanRank;
    }

    public void setClanRank(String clanRank) {
        this.clanRank = clanRank;
    }

    public String getPreviousClanRank() {
        return previousClanRank;
    }

    public void setPreviousClanRank(String previousClanRank) {
        this.previousClanRank = previousClanRank;
    }

    public String getDonations() {
        return donations;
    }

    public void setDonations(String donations) {
        this.donations = donations;
    }

    public String getDonationsReceived() {
        return donationsReceived;
    }

    public void setDonationsReceived(String donationsReceived) {
        this.donationsReceived = donationsReceived;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeString(this.name);
        dest.writeString(this.role);
        dest.writeString(this.expLevel);
        dest.writeString(this.trophies);
        dest.writeString(this.clanRank);
        dest.writeString(this.previousClanRank);
        dest.writeString(this.donations);
        dest.writeString(this.donationsReceived);
        dest.writeParcelable(this.clan, flags);
        dest.writeParcelable(this.league, flags);
    }

    public Player() {
    }

    protected Player(Parcel in) {
        this.tag = in.readString();
        this.name = in.readString();
        this.role = in.readString();
        this.expLevel = in.readString();
        this.trophies = in.readString();
        this.clanRank = in.readString();
        this.previousClanRank = in.readString();
        this.donations = in.readString();
        this.donationsReceived = in.readString();
        this.clan = in.readParcelable(Clan.class.getClassLoader());
        this.league = in.readParcelable(League.class.getClassLoader());
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}

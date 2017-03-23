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
import java.util.ArrayList;

/**
 * Created by ashish on 23-03-2016.
 */
public class ClanDetails implements Parcelable {

    public Clan clanInfo;
    public ArrayList<Player> memberList;

    public Clan getClanInfo() {
        return clanInfo;
    }

    public void setClanInfo(Clan clanInfo) {
        this.clanInfo = clanInfo;
    }

    public ArrayList<Player> getMemberList() {
        return memberList;
    }

    public void setMemberList(
            ArrayList<Player> memberList) {
        this.memberList = memberList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.clanInfo, flags);
        dest.writeList(this.memberList);
    }

    public ClanDetails() {
    }

    protected ClanDetails(Parcel in) {
        this.clanInfo = in.readParcelable(Clan.class.getClassLoader());
        this.memberList = new ArrayList<Player>();
        in.readList(this.memberList, Player.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClanDetails> CREATOR
            = new Parcelable.Creator<ClanDetails>() {

        @Override
        public ClanDetails createFromParcel(Parcel source) {
            return new ClanDetails(source);
        }

        @Override
        public ClanDetails[] newArray(int size) {
            return new ClanDetails[size];
        }
    };
}

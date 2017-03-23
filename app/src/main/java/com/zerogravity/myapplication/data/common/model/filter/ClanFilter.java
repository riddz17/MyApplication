package com.zerogravity.myapplication.data.common.model.filter;

import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;

public class ClanFilter implements SearchFilter {


    private String name = "";
    private String warFrequency = "";
    private String locationId = "";
    private String minMembers = "";
    private String maxMembers = "";
    private String minClanPoints = "";
    private String minClanLevel = "";
    private String limit = "";
    private String after = "";
    private String before = "";

    private HashMap<String, String> nonEmptyParamList;

    @Override
    public String getRestQueryString(String rootUri) {

//        if(nonEmptyParamList!=null) {
        Uri.Builder uriBuilder = Uri.parse(rootUri).buildUpon();
        if (!TextUtils.isEmpty(name)) {
            uriBuilder.appendQueryParameter("name", name);
        }
        if (!TextUtils.isEmpty(warFrequency)) {
            uriBuilder.appendQueryParameter("warFrequency", warFrequency);
        }
        if (!TextUtils.isEmpty(locationId)) {
            uriBuilder.appendQueryParameter("locationId", locationId);
        }
        if (!TextUtils.isEmpty(minMembers)) {
            uriBuilder.appendQueryParameter("minMembers", minMembers);
        }
        if (!TextUtils.isEmpty(maxMembers)) {
            uriBuilder.appendQueryParameter("maxMembers", maxMembers);
        }
        if (!TextUtils.isEmpty(minClanPoints)) {
            uriBuilder.appendQueryParameter("minClanPoints", minClanPoints);
        }
        if (!TextUtils.isEmpty(minClanLevel)) {
            uriBuilder.appendQueryParameter("minClanLevel", minClanLevel);
        }
        if (!TextUtils.isEmpty(limit)) {
            uriBuilder.appendQueryParameter("limit", limit);
        }
        if (!TextUtils.isEmpty(after)) {
            uriBuilder.appendQueryParameter("after", after);
        }
        if (!TextUtils.isEmpty(limit)) {
            uriBuilder.appendQueryParameter("limit", limit);
        }

        return uriBuilder.build().toString();

    }

    private ClanFilter(String name,
                       String warFrequency,
                       String locationId,
                       String minMembers,
                       String maxMembers,
                       String minClanPoints,
                       String minClanLevel,
                       String limit,
                       String after,
                       String before) {
        this.name = name;
        this.warFrequency = warFrequency;
        this.locationId = locationId;
        this.minMembers = minMembers;
        this.maxMembers = maxMembers;
        this.minClanPoints = minClanPoints;
        this.minClanLevel = minClanLevel;
        this.limit = limit;
        this.after = after;
        this.before = before;
    }

    private void buildNonEmptyParamList() {
        nonEmptyParamList = new HashMap<String, String>();
        if (!TextUtils.isEmpty(name)) {
            nonEmptyParamList.put("name", name);
        }
        if (!TextUtils.isEmpty(warFrequency)) {
            nonEmptyParamList.put("warFrequency", warFrequency);
        }
        if (!TextUtils.isEmpty(locationId)) {
            nonEmptyParamList.put("locationId", locationId);
        }
        if (!TextUtils.isEmpty(minMembers)) {
            nonEmptyParamList.put("minMembers", minMembers);
        }
        if (!TextUtils.isEmpty(maxMembers)) {
            nonEmptyParamList.put("maxMembers", maxMembers);
        }
        if (!TextUtils.isEmpty(minClanPoints)) {
            nonEmptyParamList.put("minClanPoints", minClanPoints);
        }
        if (!TextUtils.isEmpty(minClanLevel)) {
            nonEmptyParamList.put("minClanLevel", minClanLevel);
        }
        if (!TextUtils.isEmpty(limit)) {
            nonEmptyParamList.put("limit", limit);
        }
        if (!TextUtils.isEmpty(after)) {
            nonEmptyParamList.put("after", after);
        }
        if (!TextUtils.isEmpty(limit)) {
            nonEmptyParamList.put("before", before);
        }
    }

    public static class Builder {
        private String name = "";
        private String warFrequency = "";
        private String locationId = "";
        private String minMembers = "";
        private String maxMembers = "";
        private String minClanPoints = "";
        private String minClanLevel = "";
        private String limit = "";
        private String after = "";
        private String before = "";


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWarFrequency(String warFrequency) {
            this.warFrequency = warFrequency;
            return this;
        }

        public Builder setLocationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder setMinMembers(String minMembers) {
            this.minMembers = minMembers;
            return this;
        }

        public Builder setMaxMembers(String maxMembers) {
            this.maxMembers = maxMembers;
            return this;
        }

        public Builder setMinClanPoints(String minClanPoints) {
            this.minClanPoints = minClanPoints;
            return this;
        }

        public Builder setMinClanLevel(String minClanLevel) {
            this.minClanLevel = minClanLevel;
            return this;
        }

        public Builder setLimit(String limit) {
            this.limit = limit;
            return this;
        }

        public Builder setAfter(String after) {
            this.after = after;
            return this;
        }

        public Builder setBefore(String before) {
            this.before = before;
            return this;
        }

        public ClanFilter build() {
            if (TextUtils.isEmpty(name)
                    && TextUtils.isEmpty(warFrequency)
                    && TextUtils.isEmpty(locationId)
                    && TextUtils.isEmpty(minMembers)
                    && TextUtils.isEmpty(maxMembers)
                    && TextUtils.isEmpty(minClanPoints)
                    && TextUtils.isEmpty(minClanLevel)
                    && TextUtils.isEmpty(limit)
                    && TextUtils.isEmpty(after)
                    && TextUtils.isEmpty(before)
                    ) {
                throw new IllegalArgumentException("At least one parameter must be provided");
            }
            ClanFilter instance = new ClanFilter(name,
                    warFrequency,
                    locationId,
                    minMembers,
                    maxMembers,
                    minClanPoints,
                    minClanLevel,
                    limit,
                    after,
                    before);
            instance.buildNonEmptyParamList();
            return instance;
        }
    }


}

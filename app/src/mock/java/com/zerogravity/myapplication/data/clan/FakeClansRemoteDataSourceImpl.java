/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zerogravity.myapplication.data.clan;

import android.support.annotation.NonNull;
import com.google.common.collect.Lists;
import com.zerogravity.myapplication.data.common.model.Clan;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
public class FakeClansRemoteDataSourceImpl implements ClanDataSource {

  private static final Map<String, Clan> CLANS_SERVICE_DATA = new LinkedHashMap<>();

  @Inject
  public FakeClansRemoteDataSourceImpl() {
  }


  @Override
  public void getClans(@NonNull GetClanListCallback callback) {
    callback.onClanListFetched(Lists.newArrayList(CLANS_SERVICE_DATA.values()));
  }

  @Override
  public void getClan(@NonNull String clanTag, @NonNull GetClanCallback callback) {
    Clan clan = CLANS_SERVICE_DATA.get(clanTag);
    callback.onClanFetched(clan);
  }

  @Override
  public void saveClan(@NonNull Clan clan) {

  }

  @Override
  public void refreshClans() {
    // Not required because the {@link ClanRepository} handles the logic of refreshing the
    // tasks from all the available data sources.
  }

  @Override
  public void deleteAllClans() {

  }

  @Override
  public void deleteClan(@NonNull String clanTag) {

  }
}

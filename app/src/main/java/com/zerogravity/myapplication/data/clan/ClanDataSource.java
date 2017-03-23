package com.zerogravity.myapplication.data.clan;


import android.support.annotation.NonNull;
import com.zerogravity.myapplication.data.common.RepositoryBaseCallback;
import com.zerogravity.myapplication.data.common.model.Clan;
import java.util.List;

public interface ClanDataSource {
  interface GetClanListCallback extends RepositoryBaseCallback {
    void onClanListFetched(List<Clan> clans);
  }

  interface GetClanCallback extends RepositoryBaseCallback {
    void onClanFetched(Clan clan);
  }

  void getClans(@NonNull GetClanListCallback callback);

  void getClan(@NonNull String clanTag,@NonNull GetClanCallback callback);

  void saveClan(@NonNull Clan clan);

  void refreshClans();

  void deleteAllClans();

  void deleteClan(@NonNull String clanTag);

}

package com.zerogravity.myapplication.data.clan;


import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.zerogravity.myapplication.data.common.Local;
import com.zerogravity.myapplication.data.common.Remote;
import com.zerogravity.myapplication.data.common.model.Clan;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Concrete implementation to load {@link Clan} from the data sources into a cache.
 * <p/>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
@Singleton
public class ClanRepository implements ClanDataSource {

  @NonNull
  private final ClanDataSource clanRemoteDataSource;

  @NonNull
  private final ClanDataSource clanLocalDataSource;

  /**
   * This variable has package local visibility so it can be accessed from tests.
   */
  @VisibleForTesting
  @Nullable
  Map<String, Clan> cachedClans;

  /**
   * Marks the cache as invalid, to force an update the next time data is requested. This variable
   * has package local visibility so it can be accessed from tests.
   */
  @VisibleForTesting
  boolean cacheIsDirty = false;

  /**
   * By marking the constructor with {@code @Inject}, Dagger will try to inject the dependencies
   * required to create an instance of the ClanRepository. Because {@link ClanDataSource} is an
   * interface, we must provide to Dagger a way to build those arguments, this is done in
   * {@link ClanRepositoryModule}.
   * <P>
   * When two arguments or more have the same type, we must provide to Dagger a way to
   * differentiate them. This is done using a qualifier.
   * <p>
   * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
   * with {@code @Nullable} values.
   */
  @Inject
  public ClanRepository(@Remote ClanDataSource _clanRemoteDataSource,
      @Local ClanDataSource _clanLocalDataSource) {
    clanRemoteDataSource = checkNotNull(_clanRemoteDataSource);
    clanLocalDataSource = checkNotNull(_clanLocalDataSource);
  }


  /**
   * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
   * available first.
   */
  @Override
  public void getClans(@NonNull final GetClanListCallback callback) {

    // Respond immediately with cache if available and not dirty
    if (cachedClans != null && !cacheIsDirty) {
      callback.onClanListFetched(new ArrayList<>(cachedClans.values()));
      return;
    }

    if (cacheIsDirty) {
      // If the cache is dirty we need to fetch new data from the network.
      getClansFromRemoteDataSource(callback);
    } else {
      // Query the local storage if available. If not, query the network.
      clanLocalDataSource.getClans(new GetClanListCallback() {
        @Override
        public void onClanListFetched(List<Clan> clans) {
          refreshCache(clans);
          callback.onClanListFetched(new ArrayList<>(cachedClans.values()));
        }

        @Override
        public void onError(Throwable t) {
          getClansFromRemoteDataSource(callback);
        }

      });
    }

  }

  /**
   * Gets clans from local data source (sqlite) unless the table is new or empty. In that case it
   * uses the network data source. This is done to simplify the sample.
   */
  @Override
  public void getClan(@NonNull final String clanTag, @NonNull final GetClanCallback callback) {

    Clan cachedClan = getClanWithTag(clanTag);

    // Respond immediately with cache if available
    if (cachedClan != null) {
      callback.onClanFetched(cachedClan);
      return;
    }

    // Load from server/persisted if needed.

    // Is the clan in the local data source? If not, query the network.
    clanLocalDataSource.getClan(clanTag, new GetClanCallback() {
      @Override
      public void onClanFetched(Clan clan) {

        callback.onClanFetched(clan);
      }

      @Override
      public void onError(Throwable t) {
        getClanByTagFromRemoteDataSource(clanTag, callback);
      }


    });
  }

  @Override
  public void saveClan(@NonNull Clan clan) {
    clanRemoteDataSource.saveClan(clan);
    clanLocalDataSource.saveClan(clan);

    // Do in memory cache update to keep the app UI up to date
    if (cachedClans == null) {
      cachedClans = new LinkedHashMap<>();
    }
    cachedClans.put(clan.getTag(), clan);
  }

  @Override
  public void refreshClans() {
    cacheIsDirty = true;
  }

  @Override
  public void deleteAllClans() {
    clanRemoteDataSource.deleteAllClans();
    clanLocalDataSource.deleteAllClans();

    if (cachedClans == null) {
      cachedClans = new LinkedHashMap<>();
    }
    cachedClans.clear();
  }

  @Override
  public void deleteClan(@NonNull String clanTag) {
    clanRemoteDataSource.deleteClan(checkNotNull(clanTag));
    clanLocalDataSource.deleteClan(checkNotNull(clanTag));

    if( cachedClans != null) {
      cachedClans.remove(clanTag);
    }
  }

  @Nullable
  private Clan getClanWithTag(@NonNull String tag) {
    if (cachedClans == null || cachedClans.isEmpty()) {
      return null;
    } else {
      return cachedClans.get(tag);
    }
  }

  private void getClanByTagFromRemoteDataSource(@NonNull final String clanTag,
      @NonNull final GetClanCallback callback) {
    clanRemoteDataSource.getClan(clanTag, new GetClanCallback() {
      @Override
      public void onClanFetched(Clan clan) {
        refreshCacheByClanTag(clan);
        refreshLocalDataSourceByClanTag(clan);
        callback.onClanFetched(cachedClans.get(clanTag));
      }

      @Override
      public void onError(Throwable t) {
        callback.onError(t);
      }

    });
  }

  private void refreshCacheByClanTag(Clan clan) {
    if (cachedClans == null) {
      cachedClans = new LinkedHashMap<>();
    }
    cachedClans.remove(clan.getTag());
    cachedClans.put(clan.getTag(), clan);
  }

  private void refreshLocalDataSourceByClanTag(Clan clan) {
    clanLocalDataSource.saveClan(clan);
  }

  private void getClansFromRemoteDataSource(@NonNull final GetClanListCallback callback) {
    clanRemoteDataSource.getClans(new GetClanListCallback() {
      @Override
      public void onClanListFetched(List<Clan> clans) {
        refreshCache(clans);
        refreshLocalDataSource(clans);
        callback.onClanListFetched(new ArrayList<>(cachedClans.values()));
      }

      @Override
      public void onError(Throwable t) {
        callback.onError(t);
      }

    });
  }

  private void refreshCache(List<Clan> clans) {
    if (cachedClans == null) {
      cachedClans = new LinkedHashMap<>();
    }
    cachedClans.clear();
    for (Clan clan : clans) {
      cachedClans.put(clan.getTag(), clan);
    }
    cacheIsDirty = false;
  }

  private void refreshLocalDataSource(List<Clan> clans) {
    clanLocalDataSource.deleteAllClans();
    for (Clan clan : clans) {
      clanLocalDataSource.saveClan(clan);
    }
  }
}

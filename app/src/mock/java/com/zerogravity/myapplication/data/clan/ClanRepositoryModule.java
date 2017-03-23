package com.zerogravity.myapplication.data.clan;

import com.zerogravity.myapplication.data.common.Local;
import com.zerogravity.myapplication.data.common.Remote;
import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;

@Module
abstract class ClanRepositoryModule {
  @Singleton
  @Binds
  @Local
  abstract ClanDataSource provideClanLocalDataSource(ClanLocalDataSourceImpl dataSource);

  @Singleton
  @Binds
  @Remote
  abstract ClanDataSource provideClanRemoteDataSource(FakeClansRemoteDataSourceImpl dataSource);
}

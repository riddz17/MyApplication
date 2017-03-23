package com.zerogravity.myapplication.data.clan;

import com.zerogravity.myapplication.data.Local;
import com.zerogravity.myapplication.data.Remote;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * This is used by Dagger to inject the required arguments into the {@link ClanRepository}.
 */
@Module
public class ClanRepositoryModule {

    @Singleton
    @Provides
    @Local
    ClanDataSource provideClanLocalDataSource() {
        return new ClanLocalDataSourceImpl();
    }

    @Singleton
    @Provides
    @Remote
    ClanDataSource provideClanRemoteDataSource() {
        return new ClanRemoteDataSourceImpl();
    }


}

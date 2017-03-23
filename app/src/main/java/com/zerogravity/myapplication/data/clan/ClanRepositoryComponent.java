package com.zerogravity.myapplication.data.clan;


import com.zerogravity.myapplication.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ClanRepositoryComponent {

}

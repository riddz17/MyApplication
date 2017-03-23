package com.zerogravity.myapplication;//package com.zerogravity.myapplication.app;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
  private final Context mContext;

  ApplicationModule(Context context) {
    this.mContext = context.getApplicationContext();
  }

  @Provides
  Context provideApplicationContext() {
    return mContext;
  }


}

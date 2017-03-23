package com.zerogravity.myapplication;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import com.zerogravity.myapplication.utils.CrashReportingTree;
import timber.log.Timber;
import timber.log.Timber.DebugTree;


public class AppController extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    installLeakCanary();


    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      Timber.plant(new CrashReportingTree());
    }

  }

  private void installLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
  }


}

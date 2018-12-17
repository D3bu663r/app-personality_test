package com.rafael.personalitytest;

import android.app.Application;
import android.util.Log;

import com.rafael.personalitytest.di.AppComponent;
import com.rafael.personalitytest.di.DaggerAppComponent;
import com.rafael.personalitytest.di.module.AppModule;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                    if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                        return;
                    }
                }
            });
        }
    }

    public AppComponent getComponent() {
        return component;
    }
}

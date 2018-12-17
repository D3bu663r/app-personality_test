package com.rafael.personalitytest.di.module;

import android.app.Application;

import com.rafael.personalitytest.data.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    @Singleton
    @Provides
    PreferencesHelper providePreferencesHelper(Application application) {
        return new PreferencesHelper(application);
    }
}

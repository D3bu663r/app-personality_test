package com.rafael.personalitytest.di.module;

import com.rafael.personalitytest.data.network.api.AuthApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Singleton
    @Provides
    AuthApi provideAuth(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}

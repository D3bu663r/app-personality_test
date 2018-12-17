package com.rafael.personalitytest.di.module;

import com.rafael.personalitytest.data.network.api.AuthApi;
import com.rafael.personalitytest.data.network.service.AuthService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    AuthService provideAuthService(AuthApi api) {
        return new AuthService(api);
    }
}

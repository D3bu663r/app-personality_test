package com.rafael.personalitytest.di.module;

import com.rafael.personalitytest.data.PreferencesHelper;
import com.rafael.personalitytest.data.network.service.AuthService;
import com.rafael.personalitytest.ui.login.LoginViewModel;
import com.rafael.personalitytest.ui.register.RegisterViewModel;
import com.rafael.personalitytest.ui.splash.SplashViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Singleton
    @Provides
    LoginViewModel provideLoginViewModel(AuthService authService, PreferencesHelper preferencesHelper) {
        return new LoginViewModel(authService, preferencesHelper);
    }

    @Singleton
    @Provides
    RegisterViewModel provideRegisterViewModel(AuthService authService, PreferencesHelper preferencesHelper) {
        return new RegisterViewModel(authService, preferencesHelper);
    }

    @Singleton
    @Provides
    SplashViewModel provideSplashViewModel(AuthService authService) {
        return new SplashViewModel(authService);
    }
}

package com.rafael.personalitytest.di;

import com.rafael.personalitytest.di.module.ApiModule;
import com.rafael.personalitytest.di.module.AppModule;
import com.rafael.personalitytest.di.module.NetworkModule;
import com.rafael.personalitytest.di.module.PreferencesModule;
import com.rafael.personalitytest.di.module.ServiceModule;
import com.rafael.personalitytest.di.module.ViewModelModule;
import com.rafael.personalitytest.ui.login.LoginActivity;
import com.rafael.personalitytest.ui.register.RegisterActivity;
import com.rafael.personalitytest.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        PreferencesModule.class,
        NetworkModule.class,
        ApiModule.class,
        ServiceModule.class,
        ViewModelModule.class
})
public interface AppComponent {
    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);
}

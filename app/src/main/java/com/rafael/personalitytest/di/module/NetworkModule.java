package com.rafael.personalitytest.di.module;

import com.google.gson.GsonBuilder;
import com.rafael.personalitytest.BuildConfig;
import com.rafael.personalitytest.data.PreferencesHelper;
import com.rafael.personalitytest.model.Token;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(PreferencesHelper preferencesHelper) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Token token = preferencesHelper.getToken();
                    Request request = chain.request();

                    request = request.newBuilder()
                            .build();

                    Headers moreHeaders = request.headers().newBuilder()
                            .add("Authorization", String.format("%s %s", token.getType(), token.getAccessToken()))
                            .add("Content-Type", "application/json")
                            .build();

                    request = request.newBuilder().headers(moreHeaders).build();

                    return chain.proceed(request);
                }).build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}

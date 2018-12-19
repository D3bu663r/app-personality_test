package com.rafael.personalitytest.di.module;

import com.rafael.personalitytest.data.network.api.AnswerApi;
import com.rafael.personalitytest.data.network.api.AuthApi;
import com.rafael.personalitytest.data.network.api.QuestionApi;
import com.rafael.personalitytest.data.network.service.AnswerService;
import com.rafael.personalitytest.data.network.service.AuthService;
import com.rafael.personalitytest.data.network.service.QuestionService;

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

    @Singleton
    @Provides
    QuestionService provideQuestionService(QuestionApi api) {
        return new QuestionService(api);
    }

    @Singleton
    @Provides
    AnswerService provideAnswerService(AnswerApi api) {
        return new AnswerService(api);
    }
}

package com.rafael.personalitytest.di.module;

import com.rafael.personalitytest.data.PreferencesHelper;
import com.rafael.personalitytest.data.network.service.AnswerService;
import com.rafael.personalitytest.data.network.service.AuthService;
import com.rafael.personalitytest.data.network.service.QuestionService;
import com.rafael.personalitytest.ui.answer.AnswerViewModel;
import com.rafael.personalitytest.ui.login.LoginViewModel;
import com.rafael.personalitytest.ui.question.QuestionViewModel;
import com.rafael.personalitytest.ui.register.RegisterViewModel;
import com.rafael.personalitytest.ui.splash.SplashViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    LoginViewModel provideLoginViewModel(AuthService authService, PreferencesHelper preferencesHelper) {
        return new LoginViewModel(authService, preferencesHelper);
    }

    @Provides
    RegisterViewModel provideRegisterViewModel(AuthService authService, PreferencesHelper preferencesHelper) {
        return new RegisterViewModel(authService, preferencesHelper);
    }

    @Provides
    SplashViewModel provideSplashViewModel(AuthService authService) {
        return new SplashViewModel(authService);
    }

    @Provides
    QuestionViewModel provideQuestionViewModel(QuestionService questionService) {
        return new QuestionViewModel(questionService);
    }

    @Provides
    AnswerViewModel provideAnswerViewModel(AnswerService answerService) {
        return new AnswerViewModel(answerService);
    }
}

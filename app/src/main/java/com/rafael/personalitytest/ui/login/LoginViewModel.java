package com.rafael.personalitytest.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rafael.personalitytest.data.PreferencesHelper;
import com.rafael.personalitytest.data.network.service.AuthService;
import com.rafael.personalitytest.model.Token;
import com.rafael.personalitytest.utils.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LoginViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginProgress = new MutableLiveData<>();
    private AuthService authService;
    private PreferencesHelper preferencesHelper;


    public LoginViewModel(AuthService authService, PreferencesHelper preferencesHelper) {
        this.authService = authService;
        this.preferencesHelper = preferencesHelper;
    }

    MutableLiveData<String> getLoginError() {
        return loginError;
    }

    MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    MutableLiveData<Boolean> getLoginProgress() {
        return loginProgress;
    }

    public void login(String username, String password) {
        disposables.add(authService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> loginProgress.setValue(true))
                .doAfterTerminate(() -> loginProgress.setValue(false))
                .subscribeWith(new DisposableSingleObserver<Token>() {

                    @Override
                    public void onSuccess(Token token) {
                        preferencesHelper.putToken(token);
                        loginStatus.setValue(true);
                        Timber.d("Usuário logado com sucesso");
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = Util.getErrorMessage(e);
                        loginError.setValue(message);
                        Timber.e(message);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}

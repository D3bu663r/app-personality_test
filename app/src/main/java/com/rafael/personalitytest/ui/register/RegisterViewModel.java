package com.rafael.personalitytest.ui.register;

import android.arch.lifecycle.MutableLiveData;

import com.rafael.personalitytest.data.PreferencesHelper;
import com.rafael.personalitytest.data.network.service.AuthService;
import com.rafael.personalitytest.model.Token;
import com.rafael.personalitytest.utils.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class RegisterViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<String> registerError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerProgress = new MutableLiveData<>();
    private AuthService authService;
    private PreferencesHelper preferencesHelper;


    public RegisterViewModel(AuthService authService, PreferencesHelper preferencesHelper) {
        this.authService = authService;
        this.preferencesHelper = preferencesHelper;
    }

    MutableLiveData<String> getRegisterError() {
        return registerError;
    }

    MutableLiveData<Boolean> getRegisterStatus() {
        return registerStatus;
    }

    MutableLiveData<Boolean> getRegisterProgress() {
        return registerProgress;
    }

    public void register(String name, String username, String password) {
        disposables.add(authService.register(name, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> registerProgress.setValue(true))
                .doAfterTerminate(() -> registerProgress.setValue(false))
                .subscribeWith(new DisposableSingleObserver<Token>() {

                    @Override
                    public void onSuccess(Token token) {
                        preferencesHelper.putToken(token);
                        registerStatus.setValue(true);
                        Timber.d("Usuário registrado com sucesso");
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = Util.getErrorMessage(e);
                        registerError.setValue(message);
                        Timber.e(message);
                    }
                }));
    }
}

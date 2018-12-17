package com.rafael.personalitytest.ui.splash;

import android.arch.lifecycle.MutableLiveData;

import com.rafael.personalitytest.data.network.service.AuthService;
import com.rafael.personalitytest.utils.Util;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import timber.log.Timber;

public class SplashViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private AuthService authService;


    public SplashViewModel(AuthService authService) {
        this.authService = authService;
    }


    MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }


    public void validateToken() {
        disposables.add(authService.validateToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {

                    @Override
                    public void onSuccess(ResponseBody value) {
                        loginStatus.setValue(true);
                        try {
                            Timber.d(value.string());
                        } catch (Exception ignored) { }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = Util.getErrorMessage(e);
                        loginStatus.setValue(false);
                        Timber.e(message);
                    }
                }));
    }
}

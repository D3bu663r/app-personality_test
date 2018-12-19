package com.rafael.personalitytest.ui.answer;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rafael.personalitytest.data.network.service.AnswerService;
import com.rafael.personalitytest.utils.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import timber.log.Timber;

public class AnswerViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<String> answerError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> answerStatus = new MutableLiveData<>();
    private final MutableLiveData<Boolean> answerProgress = new MutableLiveData<>();
    private AnswerService answerService;


    public AnswerViewModel(AnswerService answerService) {
        this.answerService = answerService;
    }

    public MutableLiveData<String> getAnswerError() {
        return answerError;
    }

    public MutableLiveData<Boolean> getAnswerStatus() {
        return answerStatus;
    }

    public MutableLiveData<Boolean> getAnswerProgress() {
        return answerProgress;
    }

    public void sendAnswer(String questionId, String questionDescription, String answer) {
        disposables.add(answerService.sendAnswer(questionId, questionDescription, answer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> answerProgress.setValue(true))
                .doAfterTerminate(() -> answerProgress.setValue(false))
                .subscribeWith(new DisposableSingleObserver<ResponseBody>() {

                    @Override
                    public void onSuccess(ResponseBody body) {
                        answerStatus.setValue(true);
                        Timber.d("Resposta enviada com sucesso");
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = Util.getErrorMessage(e);
                        answerError.setValue(message);
                        Timber.e(message);
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}

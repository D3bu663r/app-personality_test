package com.rafael.personalitytest.ui.question;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.annimon.stream.Stream;
import com.rafael.personalitytest.data.network.service.QuestionService;
import com.rafael.personalitytest.model.Question;
import com.rafael.personalitytest.utils.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class QuestionViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<String> questionError = new MutableLiveData<>();
    private final MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private final MutableLiveData<List<String>> categories = new MutableLiveData<>();
    private final MutableLiveData<Boolean> questionProgress = new MutableLiveData<>();
    private QuestionService questionService;
    private List<Question> mQuestions;

    public QuestionViewModel(QuestionService questionService) {
        this.questionService = questionService;
    }

    MutableLiveData<String> getQuestionError() {
        return questionError;
    }

    MutableLiveData<List<Question>> getQuestions() {
        return questions;
    }

    public MutableLiveData<List<String>> getCategories() {
        return categories;
    }

    MutableLiveData<Boolean> getQuestionProgress() {
        return questionProgress;
    }

    public void loadQuestions() {
        disposables.add(questionService.listQuestions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> questionProgress.setValue(true))
                .doAfterTerminate(() -> questionProgress.setValue(false))
                .subscribeWith(new DisposableSingleObserver<List<Question>>() {

                    @Override
                    public void onSuccess(List<Question> data) {
                        mQuestions = data;
                        questions.setValue(data);

                        List<String> category = new ArrayList<>();
                        category.add("all");

                        category.addAll(
                                Stream.of(data)
                                        .map(Question::getCategory)
                                        .distinct()
                                        .toList());

                        categories.setValue(category);
                        Timber.d("perguntas carregadas com sucesso");
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = Util.getErrorMessage(e);
                        questionError.setValue(message);
                        Timber.e(message);
                    }
                }));
    }

    public void filterQuestions(String query) {
        if (!query.isEmpty()) {
            if (query.equals("all")) {
                questions.setValue(mQuestions);
            } else {
                questions.setValue(Stream.of(mQuestions)
                        .filter(question -> question.getCategory().equals(query))
                        .toList());
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}

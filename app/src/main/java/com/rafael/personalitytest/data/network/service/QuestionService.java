package com.rafael.personalitytest.data.network.service;

import com.rafael.personalitytest.data.network.api.QuestionApi;
import com.rafael.personalitytest.model.Question;

import java.util.List;

import io.reactivex.Single;

public class QuestionService {

    private QuestionApi questionApi;

    public QuestionService(QuestionApi questionApi) {
        this.questionApi = questionApi;
    }

    public Single<List<Question>> listQuestions() {
        return questionApi.listQuestions();
    }
}
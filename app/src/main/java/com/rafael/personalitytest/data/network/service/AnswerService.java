package com.rafael.personalitytest.data.network.service;

import com.rafael.personalitytest.data.network.api.AnswerApi;
import com.rafael.personalitytest.model.Answer;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public class AnswerService {

    private AnswerApi answerApi;

    public AnswerService(AnswerApi answerApi) {
        this.answerApi = answerApi;
    }

    public Single<ResponseBody> sendAnswer(String questionId, String questionDescription, String answer) {
        return answerApi.sendAnswer(new Answer(questionId, questionDescription, answer));
    }
}

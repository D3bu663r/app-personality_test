package com.rafael.personalitytest.data.network.api;

import com.rafael.personalitytest.model.Question;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface QuestionApi {

    @GET("questions")
    Single<List<Question>> listQuestions();
}

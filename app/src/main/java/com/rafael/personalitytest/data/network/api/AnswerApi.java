package com.rafael.personalitytest.data.network.api;

import com.rafael.personalitytest.model.Answer;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AnswerApi {

    @POST("answers")
    Single<ResponseBody> sendAnswer(@Body Answer answer);
}

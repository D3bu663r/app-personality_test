package com.rafael.personalitytest.data.network.api;

import com.rafael.personalitytest.model.Login;
import com.rafael.personalitytest.model.Register;
import com.rafael.personalitytest.model.Token;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("auth/login")
    Single<Token> login(@Body Login login);

    @POST("auth/register")
    Single<Token> register(@Body Register register);

    @POST("auth/validate/token")
    Single<ResponseBody> validateToken();
}

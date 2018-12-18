package com.rafael.personalitytest.data.network.service;

import com.rafael.personalitytest.data.network.api.AuthApi;
import com.rafael.personalitytest.model.Login;
import com.rafael.personalitytest.model.Register;
import com.rafael.personalitytest.model.Token;

import io.reactivex.Single;
import okhttp3.ResponseBody;


public class AuthService {

    private AuthApi authApi;

    public AuthService(AuthApi authApi) {
        this.authApi = authApi;
    }

    public Single<Token> login(String email, String password) {
        return authApi.login(new Login(email, password));
    }

    public Single<Token> register(String name, String email, String password) {
        return authApi.register(new Register(name, email, password));
    }

    public Single<ResponseBody> validateToken() {
        return authApi.validateToken();
    }
}

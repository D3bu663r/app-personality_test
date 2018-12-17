package com.rafael.personalitytest.utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class Util {
    public static String getErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            ResponseBody body = ((HttpException) throwable).response().errorBody();
            if (body != null) {
                try {
                    return body.string();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return throwable.getMessage();
    }
}

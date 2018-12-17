package com.rafael.personalitytest.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.rafael.personalitytest.model.Token;

public class PreferencesHelper {

    private static final String PREF_NAME = "PREF_LABS";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String TOKEN_TYPE = "TOKEN_TYPE";
    private static final String EXPIRES_IN = "EXPIRES_IN";
    private static final String SCOPE = "SCOPE";

    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void putToken(Token token) {
        sharedPreferences.edit()
                .putString(ACCESS_TOKEN, token.getAccessToken())
                .putString(TOKEN_TYPE, token.getType())
                .putInt(EXPIRES_IN, token.getExpiresIn())
                .putString(SCOPE, token.getScope())
                .apply();
    }

    public Token getToken() {
        return new Token(
                sharedPreferences.getString(ACCESS_TOKEN, ""),
                sharedPreferences.getString(TOKEN_TYPE, ""),
                sharedPreferences.getInt(EXPIRES_IN, 0),
                sharedPreferences.getString(SCOPE, ""));
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}

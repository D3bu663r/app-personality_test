package com.rafael.personalitytest.model;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String type;
    @SerializedName("expires_in")
    private int expiresIn;
    private String scope;

    public Token(String accessToken, String type, int expiresIn, String scope) {
        this.accessToken = accessToken;
        this.type = type;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

package com.rafael.personalitytest.model;

import com.google.gson.annotations.SerializedName;

public class Answer {
    @SerializedName("question_id")
    private String questionId;
    @SerializedName("question_description")
    private String questionDescription;
    private String answer;

    public Answer(String questionId, String questionDescription, String answer) {
        this.questionId = questionId;
        this.questionDescription = questionDescription;
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

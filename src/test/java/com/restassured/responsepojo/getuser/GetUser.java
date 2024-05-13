
package com.restassured.responsepojo.getuser;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class GetUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("recordingpath")
    @Expose
    private String recordingpath;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GetUser withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetUser withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GetUser withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public GetUser withRole(String role) {
        this.role = role;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public GetUser withQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public GetUser withAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public GetUser withPercentage(Integer percentage) {
        this.percentage = percentage;
        return this;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public GetUser withResult(String result) {
        this.result = result;
        return this;
    }

    public String getRecordingpath() {
        return recordingpath;
    }

    public void setRecordingpath(String recordingpath) {
        this.recordingpath = recordingpath;
    }

    public GetUser withRecordingpath(String recordingpath) {
        this.recordingpath = recordingpath;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GetUser withDate(String date) {
        this.date = date;
        return this;
    }

}

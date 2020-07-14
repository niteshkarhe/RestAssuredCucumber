package com.restassured.responsepojo.post;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("Feature")
    @Expose
    private List<String> feature = new ArrayList<String>();

    public List<String> getFeature() {
        return feature;
    }

    public void setFeature(List<String> feature) {
        this.feature = feature;
    }

    public Features withFeature(List<String> feature) {
        this.feature = feature;
        return this;
    }

}

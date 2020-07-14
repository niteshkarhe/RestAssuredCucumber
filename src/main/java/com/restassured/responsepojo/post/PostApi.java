package com.restassured.responsepojo.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostApi {

    @SerializedName("BrandName")
    @Expose
    private String brandName;
    @SerializedName("Features")
    @Expose
    private Features features;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("LaptopName")
    @Expose
    private String laptopName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public PostApi withBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public PostApi withFeatures(Features features) {
        this.features = features;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PostApi withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public PostApi withLaptopName(String laptopName) {
        this.laptopName = laptopName;
        return this;
    }
}
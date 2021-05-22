package com.example.myvib_virtual_assistant.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction {
    @SerializedName("prediction")
    @Expose
    private String prediction;

    @SerializedName("probability")
    @Expose
    private Double probability;

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}

package com.example.myvib_virtual_assistant.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {
    @SerializedName("electricity")
    @Expose
    private Integer electricity;

    @SerializedName("water")
    @Expose
    private Integer water;

    @SerializedName("phone")
    @Expose
    private Integer phone;

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}

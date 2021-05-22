package com.example.myvib_virtual_assistant.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balance {
    @SerializedName("balance")
    @Expose
    private Integer balance;

    @SerializedName("saving")
    @Expose
    private Integer saving;

    @SerializedName("credit")
    @Expose
    private Integer credit;

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getSaving() {
        return saving;
    }

    public void setSaving(Integer saving) {
        this.saving = saving;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}

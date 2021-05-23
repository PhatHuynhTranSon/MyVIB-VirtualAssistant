package com.example.myvib_virtual_assistant.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("account_id")
    @Expose
    private String accountId;

    @SerializedName("bank")
    @Expose
    private String bank;

    public Account(String name, String accountId, String bank) {
        this.name = name;
        this.accountId = accountId;
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}

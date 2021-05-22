package com.example.myvib_virtual_assistant.balance;

import com.example.myvib_virtual_assistant.data.apis.BackendAPIBuilder;

public class AccountBalanceRetrieverBuilder {
    public static AccountBalanceRetriever create() {
        return new APIAccountBalanceRetriever(BackendAPIBuilder.get());
    }
}

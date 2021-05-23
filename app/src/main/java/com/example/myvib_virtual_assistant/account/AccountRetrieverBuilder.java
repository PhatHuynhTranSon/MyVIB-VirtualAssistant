package com.example.myvib_virtual_assistant.account;

import com.example.myvib_virtual_assistant.data.apis.BackendAPIBuilder;

public class AccountRetrieverBuilder {
    public static AccountRetriever get() {
        return new APIAccountRetriever(BackendAPIBuilder.get());
    }
}

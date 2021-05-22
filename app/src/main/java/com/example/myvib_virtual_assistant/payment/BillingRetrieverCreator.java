package com.example.myvib_virtual_assistant.payment;

import com.example.myvib_virtual_assistant.data.apis.BackendAPIBuilder;

public class BillingRetrieverCreator {
    public static BillingRetriever get() {
        return new APIBillingRetriever(BackendAPIBuilder.get());
    }
}

package com.example.myvib_virtual_assistant.payment;

import com.example.myvib_virtual_assistant.data.models.Bill;

public interface BillingRetrieverListener {
    void onResult(Bill bill);
    void onError(Throwable t);
}

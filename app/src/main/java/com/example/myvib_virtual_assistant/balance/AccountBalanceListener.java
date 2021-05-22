package com.example.myvib_virtual_assistant.balance;

import com.example.myvib_virtual_assistant.data.models.Balance;

public interface AccountBalanceListener {
    void onResult(Balance balance);
    void onError(Throwable throwable);
}

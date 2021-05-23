package com.example.myvib_virtual_assistant.account;

import com.example.myvib_virtual_assistant.data.models.Account;

import java.util.List;

public interface AccountRetrieverListener {
    void onResult(List<Account> accounts);
    void onError(Throwable t);
}

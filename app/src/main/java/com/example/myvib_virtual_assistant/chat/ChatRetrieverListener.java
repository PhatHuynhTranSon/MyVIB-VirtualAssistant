package com.example.myvib_virtual_assistant.chat;

import com.example.myvib_virtual_assistant.data.models.Chat;

public interface ChatRetrieverListener {
    void onResult(Chat chat);
    void onError(Throwable t);
}

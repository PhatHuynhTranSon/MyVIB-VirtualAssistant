package com.example.myvib_virtual_assistant.chat;

public interface ChatRetriever {
    void sendChat(String sentence, ChatRetrieverListener listener);
}

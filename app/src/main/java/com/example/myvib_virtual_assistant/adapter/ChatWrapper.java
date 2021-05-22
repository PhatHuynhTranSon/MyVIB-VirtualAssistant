package com.example.myvib_virtual_assistant.adapter;

import com.example.myvib_virtual_assistant.data.models.Chat;

public class ChatWrapper {
    private Chat chat;
    private ChatEnum from;

    public ChatWrapper(Chat chat, ChatEnum from) {
        this.chat = chat;
        this.from = from;
    }

    public Chat getChat() {
        return chat;
    }

    public ChatEnum getFrom() {
        return from;
    }

    public static ChatWrapper fromUser(String message) {
        Chat chat = new Chat();
        chat.setResponse(message);
        return new ChatWrapper(chat, ChatEnum.USER);
    }

    public static ChatWrapper fromAI(Chat chat) {
        return new ChatWrapper(chat, ChatEnum.AI);
    }
}

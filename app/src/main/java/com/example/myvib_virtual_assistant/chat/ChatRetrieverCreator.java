package com.example.myvib_virtual_assistant.chat;

import android.content.Context;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.apis.BackendAPIBuilder;
import com.example.myvib_virtual_assistant.languages.Language;

public class ChatRetrieverCreator {
    public static ChatRetriever get(Context context) {
        //Get the language
        String language = Language.getInstance(context).getLanguage();

        //Get api
        BackendAPI api = BackendAPIBuilder.get();

        //Create chat retriever
        if (language.equals("vi")) {
            return new APIVietnameseChatRetriever(api);
        } else if (language.equals("en")) {
            return new APIEnglishChatRetriever(api);
        } else {
            throw new RuntimeException("Invalid language");
        }
    }
}

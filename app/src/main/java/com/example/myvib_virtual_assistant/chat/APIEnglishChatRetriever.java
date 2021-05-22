package com.example.myvib_virtual_assistant.chat;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.requests.PredictionRequestBody;

public class APIEnglishChatRetriever extends APIChatRetriever {
    public APIEnglishChatRetriever(BackendAPI mApi) {
        super(mApi);
    }

    @Override
    public void sendChat(String sentence, ChatRetrieverListener listener) {
        mListener = listener;
        mApi.getEngChat(new PredictionRequestBody(sentence))
                .enqueue(this);
    }
}

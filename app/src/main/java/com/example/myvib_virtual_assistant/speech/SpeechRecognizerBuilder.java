package com.example.myvib_virtual_assistant.speech;

import android.content.Context;

import com.example.myvib_virtual_assistant.languages.Language;

public class SpeechRecognizerBuilder {
    public static MySpeechRecognizer get(Context context) {
        //Check the language
        String language = Language.getInstance(context).getLanguage();
        //Check language
        return new MyNativeSpeechRecognizer(context, language);
    }
}

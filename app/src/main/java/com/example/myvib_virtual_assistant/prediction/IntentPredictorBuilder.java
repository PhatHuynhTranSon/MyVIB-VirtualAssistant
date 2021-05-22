package com.example.myvib_virtual_assistant.prediction;

import android.content.Context;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.apis.BackendAPIBuilder;
import com.example.myvib_virtual_assistant.languages.Language;

public class IntentPredictorBuilder {
    public static IntentPredictor create(Context context) {
        //Create api
        BackendAPI api = BackendAPIBuilder.get();

        //Get language
        String language = Language.getInstance(context).getLanguage();

        //Get correct implementation
        if (language.equals("vi")) {
            return new APIVietnameseIntentPredictor(api);
        } else if (language.equals("en")) {
            return new APIEnglishIntentPredictor(api);
        } else {
            throw new RuntimeException("Language does not match");
        }
    }
}

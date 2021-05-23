package com.example.myvib_virtual_assistant.prediction;

import android.util.Log;

public class IntentMapper {
    public static Intent mapIntent(String intent) {
        if (intent.equals("BALANCE")) {
            return Intent.BALANCE;
        } else if (intent.equals("BILL")) {
            return Intent.BILL;
        } else if (intent.equals("TRANSFER")) {
            return Intent.TRANSFER;
        } else if (intent.equals("CHAT")) {
            return Intent.CHAT;
        } else if (intent.equals("NEAREST")) {
            return Intent.NEAREST;
        } else {
            return Intent.UNKNOWN;
        }
    }
}

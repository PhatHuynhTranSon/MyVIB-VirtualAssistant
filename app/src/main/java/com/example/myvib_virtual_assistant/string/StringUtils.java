package com.example.myvib_virtual_assistant.string;

import com.example.myvib_virtual_assistant.R;

public class StringUtils {
    public static String formatCurrency(int currency) {
        return String.format("%,d VND", currency);
    }
    public static String formatCurrency(String currency) { return currency + " VND"; }

    public static String formatAccount(String account) {
        return account.replaceAll(" ", "");
    }

    public static String formatBankAndAccount(String bank, String account) {
        return String.format("%s - Account: %s", bank, account);
    }

    public static int mapLocationStatus(String status) {
        if (status.equals("Open")) {
            return R.string.location_open;
        } else if (status.equals("Closed")) {
            return R.string.location_close;
        } else if (status.equals("No information")) {
            return R.string.location_unknown;
        } else {
            throw new RuntimeException("Unknown status");
        }
    }
}

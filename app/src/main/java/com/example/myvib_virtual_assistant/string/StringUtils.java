package com.example.myvib_virtual_assistant.string;

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
}

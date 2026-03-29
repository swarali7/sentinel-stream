package com.sentinel.stream.util;

/**
 * Provides shared logic for data masking.
 **/

public class PrivacyVault {

    public static String maskSensitiveData(String data) {
        if (data == null || data.length() < 4) return "****";
        // Shows only the first 2 and last 2 characters (e.g., "AB****YZ")
        return data.substring(0, 2) + "****" + data.substring(data.length() - 2);
    }
}
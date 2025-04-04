package com.example.PokemonAPI.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidMethods {
    public static boolean isDateHtml(String s) {
        if (s == null) {
            return false;
        }

        if (s.isEmpty()) {
            return false;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            dateFormatter.parse(s);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public static boolean isPositiveInteger(String str, Boolean allowPlusSign, Boolean allowEmptyValue) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (allowEmptyValue && length == 0) {
            return true;
        }

        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '+' && allowPlusSign) {
            if (length == 1 || length > 10) {
                return false;
            }
            i = 1;
        }
        else {
            if (length > 9) {
                return false;
            }
        }

        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    public static boolean isNegativeInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1 || length > 10) {
                return false;
            }
            i = 1;
        }
        else {
            if (length > 9) {
                return false;
            }
        }

        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }
}

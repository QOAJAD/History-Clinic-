package com.qoajad.backend.utils;

public class ValidationUtils {

    // This class cannot be instantiated.
    private ValidationUtils() {}

    public static boolean requireEqual(final int left, final int right, final String message) {
        if (left == right) {
            return true;
        }
        throw new IllegalStateException(message);
    }

    public static boolean requireNotEqual(final int left, final int right, final String message) {
        if (left != right) {
            return true;
        }
        throw new IllegalStateException(message);
    }

    public static boolean requireLeftGreaterThanRight(final int left, final int right, final String message) {
        if (left > right) {
            return true;
        }
        throw new IllegalStateException(message);
    }
}

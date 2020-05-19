package com.qoajad.backend.utils;

public class ValidationUtils {

    // This class cannot be instantiated.
    private ValidationUtils() {}

    public static boolean requireEqual(final long left, final long right, final String message) {
        if (left == right) {
            return true;
        }
        throw new IllegalStateException(message);
    }

    public static boolean requireNotEqual(final long left, final long right, final String message) {
        if (left != right) {
            return true;
        }
        throw new IllegalStateException(message);
    }

    public static boolean requireLeftGreaterThanRight(final long left, final long right, final String message) {
        if (left > right) {
            return true;
        }
        throw new IllegalStateException(message);
    }
}

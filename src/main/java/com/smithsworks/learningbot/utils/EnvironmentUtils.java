package com.smithsworks.learningbot.utils;

public class EnvironmentUtils {

    public static String readEnvironment(String key) {
        String value = key != null ? System.getenv(key) : null;
        return value;
    }
}

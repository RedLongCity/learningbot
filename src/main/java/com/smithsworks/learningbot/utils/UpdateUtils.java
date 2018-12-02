package com.smithsworks.learningbot.utils;

import com.pengrad.telegrambot.model.Update;

import java.util.Objects;

public class UpdateUtils {

    public static String getMessageText(Update update) {
        return isCallBack(update) ? update.callbackQuery().data() : update.message().text();
    }

    public static Integer getUserId(Update update) {
        return isCallBack(update) ? update.callbackQuery().from().id() : update.message().from().id();
    }

    public static String getUserName(Update update) {
        return isCallBack(update) ? update.callbackQuery().from().username() : update.message().from().username();
    }

    public static long getChatId(Update update) {
        return isCallBack(update) ? update.callbackQuery().message().chat().id() : update.message().chat().id();
    }

    private static boolean isCallBack(Update update) {
        return !Objects.isNull(update.callbackQuery());
    }

}

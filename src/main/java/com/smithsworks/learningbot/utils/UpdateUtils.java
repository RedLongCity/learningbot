package com.smithsworks.learningbot.utils;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

import java.util.Objects;

public class UpdateUtils {

    public static String getMessageText(Update update) {
        return isCallBack(update) ? update.callbackQuery().data() : update.message().text();
    }

    public static Integer getUserId(Update update) {
        return isCallBack(update) ? update.callbackQuery().from().id() : update.message().from().id();
    }

    public static User getUser(Update update) {
        return isCallBack(update) ? update.callbackQuery().from() : update.message().from();
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

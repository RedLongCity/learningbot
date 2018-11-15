package com.smithsworks.learningbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

abstract public class BotWebHookHandler {

    protected boolean isStartMessage(Message message) {
        return message != null && message.text() != null && message.text().startsWith("/start");
    }

    protected boolean onStart(Message message) {
        return false;
    }

    abstract void onWebhookUpdate(Update update);

    abstract String getToken();

    abstract TelegramBot getBot();
}

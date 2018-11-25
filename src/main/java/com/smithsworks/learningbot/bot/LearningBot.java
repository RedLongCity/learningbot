package com.smithsworks.learningbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public interface LearningBot {

    Object handle(Update update);

    TelegramBot getBot();
}

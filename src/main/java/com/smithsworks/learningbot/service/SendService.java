package com.smithsworks.learningbot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.smithsworks.learningbot.bot.LearningBot;
import com.smithsworks.learningbot.builder.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SendService {

    @Autowired
    @Qualifier("simple")
    private LearningBot learningBot;

    public boolean sendMessage(Update update, Builder builder) {
        return this.sendMessage(update, builder, "");
    }

    public boolean sendMessage(Update update, Builder builder, String message) {
        Keyboard keyboard = builder.constructKeyboard();
        Long chatId = update.message().chat().id();
        SendResponse response = learningBot.getBot().execute(new SendMessage(chatId, message).replyMarkup(keyboard));
        return response.isOk() && response.errorCode() == 0;
    }
}

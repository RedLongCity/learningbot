package com.smithsworks.learningbot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.smithsworks.learningbot.bot.LearningBot;
import com.smithsworks.learningbot.builder.Builder;
import com.smithsworks.learningbot.utils.UpdateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SendService {

    @Autowired
    @Qualifier("simple")
    private LearningBot learningBot;

    public boolean sendMessage(Update update, Builder builder) {
        return this.sendMessage(update, builder, "hello");
    }

    public boolean sendMessage(Update update, Builder builder, String message) {
        Long chatId = UpdateUtils.getChatId(update);
        SendResponse response;
        if (Objects.isNull(builder)) {
            response = learningBot.getBot().execute(new SendMessage(chatId, message));
        } else {
            response = learningBot.getBot().execute(new SendMessage(chatId, message).replyMarkup(builder.constructKeyboard()));
        }
        return response.isOk() && response.errorCode() == 0;
    }
}

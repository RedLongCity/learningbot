package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.Builder;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FNewWordScheme.xml
 */
@Service
@Qualifier("newWord")
public class NewWordUpdateHandler implements UpdateHandler {

    public static final String HANDLER_NAME = "newWord";

    @Autowired
    private I18nService i18nService;

    @Override
    public State handle(UserState state, Update update) {
        State result = null;
        return result;
    }

    @Override
    public HandlingPoint getDefaultPoint() {
        return null;
    }

    @Override
    public Builder constructMainMenu(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.new.word", locale));
        return null;
    }
}

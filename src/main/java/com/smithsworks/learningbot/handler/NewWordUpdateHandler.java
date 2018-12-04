package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.Builder;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.service.SendService;
import com.smithsworks.learningbot.utils.HandlingPointUtils;
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

    @Autowired
    private SendService sendService;

    @Override
    public State handle(UserState userState, Update update) {
        HandlingPoint point = HandlingPointUtils.getSelectedHandlingPoint(userState, update);
        StringBuilder message = new StringBuilder();
        State newState = new State();
        String locale = i18nService.getLocale(userState);
        Builder builder = null;
        switch (point.methodName) {
            case "enteredWord":
                builder = this.enteredWord(message, newState, locale);
                break;
        }
        boolean wasSent = sendService.sendMessage(update, builder, message.toString());
        return wasSent ? newState : null;
    }

    @Override
    public HandlingPoint getDefaultPoint() {
        return null;
    }

    @Override
    public Builder constructMainMenu(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.new.word", locale));
        newState
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "enteredWord", "default"));
        return null;
    }

    private Builder enteredWord(StringBuilder message, State newState, String locale) {

    }
}

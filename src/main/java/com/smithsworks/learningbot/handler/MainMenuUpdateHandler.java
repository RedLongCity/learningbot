package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.Builder;
import com.smithsworks.learningbot.builder.InlineKeyboardBuilder;
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
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FMainMenuScheme.xml
 */
@Service
@Qualifier("mainMenu")
public class MainMenuUpdateHandler implements UpdateHandler {

    public static final String HANDLER_NAME = "mainMenu";

    @Autowired
    @Qualifier("inline")
    private InlineKeyboardBuilder inlineKeyboardBuilder;

    @Autowired
    private I18nService i18nService;

    @Autowired
    private SendService sendService;

    @Autowired
    @Qualifier("newWord")
    private UpdateHandler newWordUpdateHandler;

    @Override
    public State handle(UserState userState, Update update) {
        HandlingPoint point = HandlingPointUtils.getSelectedHandlingPoint(userState, update);
        StringBuilder message = new StringBuilder();
        State newState = new State();
        String locale = i18nService.getLocale(userState);
        Builder builder;
        switch (point.methodName) {
            case "addNewWord":
                builder = this.addNewWord(message, newState, locale);
                break;
            default:
                builder = this.constructMainMenu(message, newState, locale);
        }
        boolean wasSent = sendService.sendMessage(update, builder, message.toString());
        return wasSent ? newState : null;
    }

    @Override
    public HandlingPoint getDefaultPoint() {
        return new HandlingPoint("default", "default", "default");
    }

    @Override
    public Builder constructMainMenu(StringBuilder message, State newState, String locale) {
        String startTraining = i18nService.getI18nString("main.menu.start.training", locale);
        String addNewWord = i18nService.getI18nString("main.menu.add.word", locale);
        String addNewCollection = i18nService.getI18nString("main.menu.add.collection", locale);
        String settings = i18nService.getI18nString("main.menu.settings", locale);
        inlineKeyboardBuilder.addRow()
                .addButton(startTraining)
                .addButton(addNewWord)
                .addRow()
                .addButton(addNewCollection)
                .addButton(settings);
        newState
                .addHandlingPoint(getDefaultPoint())
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "startTraining", startTraining))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "addNewWord", addNewWord))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "addNewCollection", addNewCollection))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "settings", settings));
        return inlineKeyboardBuilder;
    }

    private Builder addNewWord(StringBuilder message, State newState, String locale) {
        return newWordUpdateHandler.constructMainMenu(message, newState, locale);
    }

}

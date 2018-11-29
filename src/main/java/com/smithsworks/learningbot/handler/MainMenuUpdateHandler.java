package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.InlineKeyboardBuilder;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FMainMenuScheme.xml
 */
@Service
@Qualifier("mainMenu")
public class MainMenuUpdateHandler implements UpdateHandler {

    public static final String HANDLER_NAME = "mainMenu";

    @Qualifier("inline")
    @Autowired
    private InlineKeyboardBuilder builder;

    @Autowired
    private I18nService i18nService;

    @Autowired
    private SendService sendService;

    @Override
    public State handle(UserState userState, Update update) {
        State newState = new State();
        constructMainMenu(Objects.isNull(userState) ? "ru" : userState.getLocale(), newState);
        boolean wasSent = sendService.sendMessage(update, builder);
        return wasSent ? newState : null;
    }

    @Override
    public HandlingPoint addDefaultPoint() {
        return null;
    }

    private void constructMainMenu(String locale, State newState) {
        String startTraining = i18nService.getI18nString("main.menu.start.training", locale);
        String addNewWord = i18nService.getI18nString("main.menu.add.word", locale);
        String addNewCollection = i18nService.getI18nString("main.menu.add.collection", locale);
        String settings = i18nService.getI18nString("main.menu.settings", locale);
        builder.addRow()
                .addButton(startTraining)
                .addButton(addNewWord)
                .addRow()
                .addButton(addNewCollection)
                .addButton(settings);
        newState
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "startTraining", startTraining))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "addNewWord", addNewWord))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "addNewCollection", addNewCollection))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "settings", settings));
    }
}

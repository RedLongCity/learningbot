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

/**
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FMainMenuScheme.xml
 */
@Service
@Qualifier("mainMenu")
public class MainMenuUpdateHandler implements UpdateHandler {

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
        constructMainMenu(userState.getLocale(), newState);
        boolean wasSended = sendService.sendMessage(update, builder);
        return wasSended ? newState : null;
    }

    @Override
    public HandlingPoint addDefaultPoint() {
        return null;
    }

    private void constructMainMenu(String locale, State newState) {
        String startTraining = i18nService.getI18nString("main.menu.start.training", locale);
        String addNewWord = i18nService.getI18nString("main.menu.add.word", locale);
        String addNewCollection = i18nService.getI18nString("main.menu.add.collection", locale);
        String settings = i18nService.getI18nString("main.menu.settins", locale);
        builder.addRow()
                .addButton(startTraining)
                .addButton(addNewWord)
                .addRow()
                .addButton(addNewCollection)
                .addButton(settings);
        newState
                .addHandlingPoint(new HandlingPoint("mainMenu", "startTraining", startTraining))
                .addHandlingPoint(new HandlingPoint("mainMenu", "addNewWord", addNewWord))
                .addHandlingPoint(new HandlingPoint("mainMenu", "addNewCollection", addNewCollection))
                .addHandlingPoint(new HandlingPoint("mainMenu", "settings", settings));
    }
}

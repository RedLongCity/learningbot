package com.smithsworks.learningbot.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.InlineKeyboardBuilder;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.service.SendService;
import com.smithsworks.learningbot.service.UserStateService;
import com.smithsworks.learningbot.utils.UpdateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Qualifier("mainMenuCommand")
public class MainMenuCommand implements Command {

    public static final String COMMANDNAME = "mainMenuCommand";

    @Autowired
    @Qualifier("inline")
    private InlineKeyboardBuilder inlineKeyboardBuilder;

    @Autowired
    private I18nService i18nService;

    @Autowired
    private SendService sendService;

    @Autowired
    private UserStateService userStateService;

    @Autowired
    private Command newWordCommand;

    @Autowired
    private Command startTrainingCommand;

    @Autowired
    private Command addCollectionCommand;

    @Autowired
    private Command settingsCommand;

    @Override
    public void handle(UserState userState, Update update) {
        if (Objects.isNull(userState)) {
            Message message = update.message();
            userState = new UserState(
                    message.from().username(),
                    message.from().id(),
                    new State(COMMANDNAME),
                    null,
                    "ru"
            );
            userStateService.saveUserState(userState);
            this.execute(userState, update);
        } else {
            this.delegate(userState, update);
        }
    }

    @Override
    public void delegate(UserState userState, Update update) {
        String locale = i18nService.getLocale(userState);
        if (i18nService.getI18nString("main.menu.add.word", locale)
                .equalsIgnoreCase(UpdateUtils.getMessageText(update))) {
            newWordCommand.handle(userState, update);
        } else if (i18nService.getI18nString("main.menu.start.training", locale)
                .equalsIgnoreCase(UpdateUtils.getMessageText(update))) {
            startTrainingCommand.handle(userState, update);
        } else if (i18nService.getI18nString("main.menu.add.collection", locale)
                .equalsIgnoreCase(UpdateUtils.getMessageText(update))) {
            addCollectionCommand.handle(userState, update);
        } else if (i18nService.getI18nString("main.menu.settings", locale)
                .equalsIgnoreCase(UpdateUtils.getMessageText(update))) {
            settingsCommand.handle(userState, update);
        }
    }

    @Override
    public void execute(UserState userState, Update update) {
        String locale = i18nService.getLocale(userState);
        String startTraining = i18nService.getI18nString("main.menu.start.training", locale);
        String addNewWord = i18nService.getI18nString("main.menu.add.word", locale);
        String addNewCollection = i18nService.getI18nString("main.menu.add.collection", locale);
        String settings = i18nService.getI18nString("main.menu.settings", locale);
        String welcome = i18nService.getI18nString("main.menu.welcome", locale);
        inlineKeyboardBuilder.addRow()
                .addButton(startTraining)
                .addButton(addNewWord)
                .addRow()
                .addButton(addNewCollection)
                .addButton(settings);
        sendService.sendMessage(update, inlineKeyboardBuilder, welcome);
    }
}

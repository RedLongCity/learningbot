package com.smithsworks.learningbot.command;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.InlineKeyboardBuilder;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("mainMenuCommand")
public class MainMenuCommand implements Command {

    @Autowired
    @Qualifier("inline")
    private InlineKeyboardBuilder inlineKeyboardBuilder;

    @Autowired
    private I18nService i18nService;

    @Autowired
    private SendService sendService;

    @Override
    public void handle(UserState userState, Update update) {

    }

    @Override
    public void delegate(UserState userState, Update update) {

    }

    @Override
    public void execute(UserState userState, Update update) {

    }
}

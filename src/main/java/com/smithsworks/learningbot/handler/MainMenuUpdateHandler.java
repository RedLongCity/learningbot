package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
@Qualifier("mainMenu")
/**
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FMainMenuScheme.xml
 */
public class MainMenuUpdateHandler implements UpdateHandler {
    @Override
    public State handle(UserState userState, Update update) {
        State result = null;
        return result;
    }
}

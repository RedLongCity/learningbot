package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("newWord")
/**
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FNewWordScheme.xml
 */
public class NewWordUpdateHandler implements UpdateHandler {
    @Override
    public State handle(UserState state, Update update) {
        State result = null;
        return result;
    }
}

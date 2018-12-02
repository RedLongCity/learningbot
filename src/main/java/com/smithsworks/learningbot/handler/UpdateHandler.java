package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.Builder;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;

public interface UpdateHandler {

    State handle(UserState userState, Update update);

    HandlingPoint getDefaultPoint();

    Builder constructMainMenu(StringBuilder message, State newState, String locale);
}

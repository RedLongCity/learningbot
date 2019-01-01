package com.smithsworks.learningbot.command;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.UserState;

public interface Command {

    void handle(UserState userState, Update update);

    void delegate(UserState userState, Update update);

    void execute(UserState userState, Update update);

}

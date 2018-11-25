package com.smithsworks.learningbot.builder;

import com.pengrad.telegrambot.model.request.Keyboard;

public interface Builder {

    Keyboard getKeyboard();

    void constructKeyboard();

    void constructCustomKeyboard(String... parameters);

    Builder addRow();

    Builder addButton(String leafContent);

    Builder addButton(String leafContent, String additionalConditions, String additionalValue);

}

package com.smithsworks.learningbot.builder;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier("reply")
@Scope("prototype")//TODO change to request
public class ReplyKeyboardBuilder implements Builder {

    private Keyboard keyboard;
    private List<String[]> rows;
    private List<String> currentRow;

    @Override
    public Keyboard getKeyboard() {
        return this.keyboard;
    }

    @Override
    public void constructKeyboard() {
        this.keyboard = new ReplyKeyboardMarkup((String[]) rows.toArray());
    }

    @Override
    public void constructCustomKeyboard(String... parameters) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup((String[]) rows.toArray());
        if (!Objects.isNull(parameters)) {
            if (Arrays.stream(parameters).anyMatch("oneTimeKeyboard"::equalsIgnoreCase))
                replyKeyboardMarkup.oneTimeKeyboard(true);
            if (Arrays.stream(parameters).anyMatch("resizeKeyboard"::equalsIgnoreCase))
                replyKeyboardMarkup.resizeKeyboard(true);
            if (Arrays.stream(parameters).anyMatch("selective"::equalsIgnoreCase))
                replyKeyboardMarkup.selective(true);
        }
        this.keyboard = replyKeyboardMarkup;
    }

    @Override
    public Builder addRow() {
        if (Objects.isNull(rows))
            rows = new ArrayList<>(0);
        if (Objects.isNull(currentRow))
            currentRow = new ArrayList<>(0);
        else {
            this.rows.add((String[]) currentRow.toArray());
            this.currentRow = new ArrayList<>(0);
        }
        return this;
    }

    @Override
    public Builder addButton(String s) {
        this.currentRow.add(s);
        return this;
    }

    @Override
    public Builder addButton(String s, String additionalConditions, String additionalValue) {
        return null;
    }
}

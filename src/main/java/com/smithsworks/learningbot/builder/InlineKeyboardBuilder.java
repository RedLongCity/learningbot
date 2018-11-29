package com.smithsworks.learningbot.builder;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier("inline")
@Scope("prototype")//TODO change to request
public class InlineKeyboardBuilder implements Builder {

    private List<InlineKeyboardButton[]> rows;
    private List<InlineKeyboardButton> currentRow;

    @Override
    public Keyboard constructKeyboard() {
        this.addRow();
        InlineKeyboardButton[][] in = new InlineKeyboardButton[this.rows.size()][];
        return new InlineKeyboardMarkup(this.rows.toArray(in));
    }

    @Override
    public Keyboard constructCustomKeyboard(String... parameters) {
        return null;
    }

    @Override
    public Builder addRow() {
        if (Objects.isNull(rows))
            rows = new ArrayList<>(0);
        if (Objects.isNull(currentRow))
            currentRow = new ArrayList<>(0);
        else {
            InlineKeyboardButton[] in = new InlineKeyboardButton[currentRow.size()];
            this.rows.add(currentRow.toArray(in));
            this.currentRow = new ArrayList<>(0);
        }
        return this;
    }

    @Override
    public Builder addButton(String s) {
        this.currentRow.add(new InlineKeyboardButton(s));
        return this;
    }

    @Override
    public Builder addButton(String s, String additionalConditions, String additionalValue) {
        InlineKeyboardButton button = new InlineKeyboardButton(s);
        switch (additionalConditions) {
            case "url":
                button.url(additionalValue);
                break;
            case "inline":
                button.switchInlineQuery(additionalValue);
                break;
            case "callback":
                button.callbackData(additionalValue);
                break;
        }
        this.currentRow.add(button);
        return this;
    }
}

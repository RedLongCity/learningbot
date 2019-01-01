package com.smithsworks.learningbot.utils;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.UserState;

import java.util.Objects;

public class HandlingPointUtils {

    public static HandlingPoint getSelectedHandlingPoint(UserState userState, Update update) {
        HandlingPoint point = new HandlingPoint("default", "default");
        if (!Objects.isNull(userState) && !Objects.isNull(update)) {
            String selected = UpdateUtils.getMessageText(update);
            point = userState.getCurrentState().getExtra()
                    .stream()
                    .filter(handlingPoint -> handlingPoint.getValue().equalsIgnoreCase(selected))
                    .findAny()
                    .orElse(null);
            if (Objects.isNull(point))
                point = getDefaultHandlingPoint(userState);
        }
        return point;
    }

    public static HandlingPoint getDefaultHandlingPoint(UserState userState) {
        return userState.getCurrentState().getExtra()
                .stream()
                .filter(handlingPoint -> "default".equalsIgnoreCase(handlingPoint.getValue()))
                .findAny()
                .get();
    }
}

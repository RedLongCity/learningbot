package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * https://www.youtube.com/watch?v=cJ94Be-JSDo
 */
@Service
public class Splitter {

    private static final String NEW_WORD_MARKER = "newWord";
    private static final String MAIN_MENU_MARKER = "mainMenu";

    @Autowired
    private UserStateService userStateService;

    @Qualifier(NEW_WORD_MARKER)
    @Autowired
    private UpdateHandler newWorldUpdateHanler;

    @Qualifier(MAIN_MENU_MARKER)
    @Autowired
    private UpdateHandler mainMenuUpdateHanler;

    public void split(Update update) {//TODO realise start logic
        UserState userState = userStateService.getFirstNonNull(update);
        State newState = null;
        HandlingPoint point = this.getHandlingPoint(userState, update.message().text());
        switch (point.getHandlerName()) {
            case NEW_WORD_MARKER :
                newState = newWorldUpdateHanler.handle(userState, update);
                break;
            case MAIN_MENU_MARKER :
                newState = mainMenuUpdateHanler.handle(userState, update);
                break;
        }
        userStateService.updateUserState(userState, newState);
    }

    public HandlingPoint getHandlingPoint(UserState userState, String option) {
        HandlingPoint point = userState.getCurrentState().getExtra()
                .stream()
                .filter(handlingPoint -> handlingPoint.getValue().equalsIgnoreCase(option))
                .findAny()
                .orElse(null);
        if (Objects.isNull(point))
            point = userState.getCurrentState().getExtra()
                    .stream()
                    .filter(handlingPoint -> "default".equalsIgnoreCase(handlingPoint.getValue()))
                    .findAny()
                    .get();
        return point;
    }

}

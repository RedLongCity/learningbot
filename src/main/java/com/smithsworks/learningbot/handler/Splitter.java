package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.User;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.UserService;
import com.smithsworks.learningbot.service.UserStateService;
import com.smithsworks.learningbot.utils.HandlingPointUtils;
import com.smithsworks.learningbot.utils.UpdateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    private UpdateHandler newWorldUpdateHandler;

    @Qualifier(MAIN_MENU_MARKER)
    @Autowired
    private UpdateHandler mainMenuUpdateHandler;

    @Autowired
    private UserService userService;

    public void split(Update update) {//TODO realise start logic
        User user = userService.saveOrGet(UpdateUtils.getUser(update));
        State newState;
        UserState userState;
        userState = userStateService.getFirstNonNull(user);
        HandlingPoint point = HandlingPointUtils.getSelectedHandlingPoint(userState, update);
        switch (point.getHandlerName()) {
            case NEW_WORD_MARKER:
                newState = newWorldUpdateHandler.handle(userState, update);
                break;
            case MAIN_MENU_MARKER:
                newState = mainMenuUpdateHandler.handle(userState, update);
                break;
            default:
                newState = mainMenuUpdateHandler.handle(null, update);
                userState = initNewUserState(update);
        }
        userStateService.updateUserState(userState, newState);
    }

    private UserState initNewUserState(Update update) {
        Message message = update.message();
        return new UserState(
                message.from().username(),
                message.from().id(),
                null,
                null,
                "ru"
        );
    }

}

package com.smithsworks.learningbot.service;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.data.UserStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserStateService {

    @Autowired
    private UserStateRepository repository;

    public UserState getFirstNonNull(Update update) {
        UserState result = null;
        result = repository.findByTelegramId(update.message().from().id());
        if (Objects.isNull(result))
            result = repository.findByUserName(update.message().from().username());
        return result;
    }

    public void updateUserState(UserState userState, State newState) {
        userState.setPreviousState(userState.getPreviousState());
        userState.setCurrentState(newState);
        repository.save(userState);
    }
}

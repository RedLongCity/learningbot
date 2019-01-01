package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrGet(com.pengrad.telegrambot.model.User user) {
        User entity = userRepository.findByTelegramId(user.id());
        if (Objects.isNull(entity))
            return userRepository.save(new User(
                    user.id(),
                    user.firstName(),
                    user.lastName(),
                    user.username()
            ));
        else
            return entity;
    }

    public User addCollectionToUser(User user, Collection collection) {
        user.addCollectionId(collection.getId());
        return userRepository.save(user);
    }

    public User addGroupToUser(User user, Group group) {
        user.addGroupId(group.getId());
        return userRepository.save(user);
    }



}

package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserStateRepository extends MongoRepository<UserState, String> {

    public UserState findByTelegramId(Integer telegramId);

    public UserState findByUserName(String userName);
}

package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByTelegramId(Integer telegramId);

    User findByUserName(String userName);
}

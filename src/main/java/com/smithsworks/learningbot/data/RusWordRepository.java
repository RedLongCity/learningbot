package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RusWordRepository extends MongoRepository<RusWord, String> {

    public RusWord findByWord(String word);
}

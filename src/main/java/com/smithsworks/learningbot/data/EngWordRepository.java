package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EngWordRepository extends MongoRepository<EngWord, String> {

    public EngWord findByWord(String word);
}

package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TranslateRepository extends MongoRepository<Translate, String> {

    public Translate findByEngWordId(String id);
    public Translate findByRusWordIdsContaining(String id);
}

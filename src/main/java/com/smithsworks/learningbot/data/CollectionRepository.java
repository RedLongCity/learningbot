package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionRepository extends MongoRepository<Collection, String> {

    public Collection findByTranslateIdsContaining(String id);
}

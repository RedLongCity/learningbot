package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IncorrectRepository extends MongoRepository<Incorrect, String> {

    List<Incorrect> findIncorrectsByRealationIdsIn(List<String> ids);
}

package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RelationRepository extends MongoRepository<Relation, String> {

    List<Relation> findTop3RelationsByEngWordId(String engWordId);

    List<Relation> findTop10RelationsByEngWordId(String engWordId);
}

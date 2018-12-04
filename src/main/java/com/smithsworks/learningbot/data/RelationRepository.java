package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RelationRepository extends MongoRepository<Relation, String> {

    List<Relation> findTop3ByEngWordIdOrderByWeightDesc(String engWordId);

    List<Relation> findTop10ByEngWordIdOrderByWeightDesc(String engWordId);

    Relation findByEngWordIdAndRusWordId(String engWordId, String rusWordId);

    List<Relation> findRelationsByEngWordIdAndIsCorrect(String engWordId);
}

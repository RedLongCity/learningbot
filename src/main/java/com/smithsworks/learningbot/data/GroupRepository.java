package com.smithsworks.learningbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    Group findByTitle(String title);

    List<Group> findByCollectionsIdsContains(String collectionId);
}

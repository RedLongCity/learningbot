package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.Collection;
import com.smithsworks.learningbot.data.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    public Collection saveOrGet(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection update(Collection collection) {
        return collectionRepository.save(collection);
    }


}

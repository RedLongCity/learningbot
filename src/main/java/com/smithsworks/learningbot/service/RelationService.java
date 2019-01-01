package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.Relation;
import com.smithsworks.learningbot.data.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RelationService {

    @Autowired
    private RelationRepository relationRepository;

    public Relation saveOrGet(Relation relation) {
        Relation entity = relationRepository.findByEngWordIdAndRusWordId(
                relation.getEngWordId(),
                relation.getRusWordId()
        );
        if (Objects.isNull(entity))
            return relationRepository.save(relation);
        else
            return entity;
    }

    public Relation findByEngWordIdAndRusWordId(Relation relation) {
        return relationRepository.findByEngWordIdAndRusWordId(
                relation.getEngWordId(),
                relation.getRusWordId()
        );
    }

    public Relation updateRelation(Relation relation) {
        return relationRepository.save(relation);
    }

    public List<Relation> findTop3(String engWordId) {
        return relationRepository.findTop3ByEngWordIdOrderByWeightDesc(engWordId);
    }

    public List<Relation> findTop10(String engWordId) {
        return relationRepository.findTop10ByEngWordIdOrderByWeightDesc(engWordId);
    }

}

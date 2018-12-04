package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.EngWord;
import com.smithsworks.learningbot.data.EngWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EngWordService {

    @Autowired
    private EngWordRepository engWordRepository;

    public EngWord saveNewEngWord(EngWord engWord) {
        EngWord entity = engWordRepository.findByWord(engWord.getWord());
        if (Objects.isNull(entity))
            return engWordRepository.save(engWord);
        else
            return entity;
    }

    public EngWord findEngWordByWord(EngWord engWord) {
        return engWordRepository.findByWord(engWord.getWord());
    }

    public EngWord updateEngWord(EngWord engWord) {
        return engWordRepository.save(engWord);
    }

}

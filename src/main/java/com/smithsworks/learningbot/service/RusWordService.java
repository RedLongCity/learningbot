package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.RusWord;
import com.smithsworks.learningbot.data.RusWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RusWordService {

    @Autowired
    private RusWordRepository rusWordRepository;

    public RusWord saveOrGet(RusWord rusWord) {
        RusWord entity = rusWordRepository.findByWord(rusWord.getWord());
        if (Objects.isNull(entity))
            return rusWordRepository.save(rusWord);
        else
            return entity;
    }

    public RusWord findRusWordByWord(RusWord rusWord) {
        return rusWordRepository.findByWord(rusWord.getWord());
    }

    public RusWord updateRusWord(RusWord rusWord) {
        return rusWordRepository.save(rusWord);
    }
}

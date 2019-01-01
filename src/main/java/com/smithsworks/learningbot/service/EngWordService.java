package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.EngWord;
import com.smithsworks.learningbot.data.EngWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EngWordService {

    @Autowired
    private EngWordRepository engWordRepository;

    @Autowired
    private I18nService i18nService;

    public EngWord saveOrGet(EngWord engWord) {
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

    public List<String> getClassesOfWord(EngWord engWord, String locale) {
        List<String> result = null;
        if (!Objects.isNull(engWord) && !Objects.isNull(locale)) {
            result = new ArrayList<>();
            if (engWord.isNoun())
                result.add(i18nService.getI18nString("new.word.noun", locale));
            if (engWord.isAdjective())
                result.add(i18nService.getI18nString("new.word.adjective", locale));
            if (engWord.isNumeral())
                result.add(i18nService.getI18nString("new.word.numeral", locale));
            if (engWord.isAdverb())
                result.add(i18nService.getI18nString("new.word.adverb", locale));
            if (engWord.isPreposition())
                result.add(i18nService.getI18nString("new.word.preposition", locale));
            if (engWord.isConjunction())
                result.add(i18nService.getI18nString("new.word.conjunction", locale));
            if (engWord.isInterjection())
                result.add(i18nService.getI18nString("new.word.interjection", locale));
        }
        return result;
    }

}

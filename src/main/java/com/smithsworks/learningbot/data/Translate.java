package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Translate {

    @Id
    public String id;

    public String EngWordId;

    public List<String> RusWordIds;

    public Translate() {
    }

    public Translate(EngWord engWord, List<RusWord> rusWords) {
        this.EngWordId = engWord.getId();
        this.RusWordIds = Objects.isNull(rusWords)
                ? null
                : rusWords.stream().map(RusWord::getId).collect(Collectors.toList());
    }

    public Translate(EngWord engWord) {
        if (!Objects.isNull(engWord))
            this.EngWordId = engWord.getId();
    }

    public String getEngWordId() {
        return EngWordId;
    }

    public void setEngWordId(String engWordId) {
        EngWordId = engWordId;
    }

    public List<String> getRusWordIds() {
        return RusWordIds;
    }

    public void setRusWordIds(List<String> rusWordIds) {
        RusWordIds = rusWordIds;
    }

    public Translate addRusWord(RusWord rusWord) {
        if (Objects.isNull(this.RusWordIds)) {
            this.RusWordIds = new ArrayList<>();
        }
        if (!Objects.isNull(rusWord))
            this.RusWordIds.add(rusWord.getId());
        return this;
    }

    @Override
    public String toString() {
        return "Translate{" +
                "id='" + id + '\'' +
                ", EngWordId='" + EngWordId + '\'' +
                ", RusWordIds=" + RusWordIds +
                '}';
    }
}

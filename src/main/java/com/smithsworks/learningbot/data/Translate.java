package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Translate {

    @Id
    public String id;

    public String engWordId;

    public List<String> rusWordIds;

    public Translate() {
    }

    public Translate(EngWord engWord, List<RusWord> rusWords) {
        this.engWordId = engWord.getId();
        this.rusWordIds = Objects.isNull(rusWords)
                ? null
                : rusWords.stream().map(RusWord::getId).collect(Collectors.toList());
    }

    public Translate(EngWord engWord) {
        if (!Objects.isNull(engWord))
            this.engWordId = engWord.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngWordId() {
        return engWordId;
    }

    public void setEngWordId(String engWordId) {
        engWordId = engWordId;
    }

    public List<String> getRusWordIds() {
        return rusWordIds;
    }

    public void setRusWordIds(List<String> rusWordIds) {
        rusWordIds = rusWordIds;
    }

    public Translate addRusWord(RusWord rusWord) {
        if (Objects.isNull(this.rusWordIds)) {
            this.rusWordIds = new ArrayList<>();
        }
        if (!Objects.isNull(rusWord))
            this.rusWordIds.add(rusWord.getId());
        return this;
    }

    @Override
    public String toString() {
        return "Translate{" +
                "id='" + id + '\'' +
                ", EngWordId='" + engWordId + '\'' +
                ", RusWordIds=" + rusWordIds +
                '}';
    }
}

package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

public class EngWord {

    @Id
    public String id;

    public String word;

    public EngWord() {
    }

    public EngWord(String word) {
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "EngWord{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}

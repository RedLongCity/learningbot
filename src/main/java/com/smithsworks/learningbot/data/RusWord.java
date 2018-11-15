package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

public class RusWord {

    @Id
    public String id;

    public String word;

    public RusWord() {
    }

    public RusWord(String word) {
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
        return "RusWord{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}

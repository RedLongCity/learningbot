package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

public class EngWord {

    @Id
    public String id;

    public String word;

    public boolean isNoun;

    public boolean isAdjective;

    public boolean isNumeral;

    public boolean isAdverb;

    public boolean isArticle;

    public boolean isPreposition;

    public boolean isConjunction;

    public boolean isInterjection;

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

    public boolean isNoun() {
        return isNoun;
    }

    public void setNoun(boolean noun) {
        isNoun = noun;
    }

    public boolean isAdjective() {
        return isAdjective;
    }

    public void setAdjective(boolean adjective) {
        isAdjective = adjective;
    }

    public boolean isNumeral() {
        return isNumeral;
    }

    public void setNumeral(boolean numeral) {
        isNumeral = numeral;
    }

    public boolean isAdverb() {
        return isAdverb;
    }

    public void setAdverb(boolean adverb) {
        isAdverb = adverb;
    }

    public boolean isArticle() {
        return isArticle;
    }

    public void setArticle(boolean article) {
        isArticle = article;
    }

    public boolean isPreposition() {
        return isPreposition;
    }

    public void setPreposition(boolean preposition) {
        isPreposition = preposition;
    }

    public boolean isConjunction() {
        return isConjunction;
    }

    public void setConjunction(boolean conjunction) {
        isConjunction = conjunction;
    }

    public boolean isInterjection() {
        return isInterjection;
    }

    public void setInterjection(boolean interjection) {
        isInterjection = interjection;
    }

    @Override
    public String toString() {
        return "EngWord{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}

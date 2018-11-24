package com.smithsworks.learningbot.data;

public class Option {

    private boolean text;

    private boolean isCorrect;

    private String relationId;

    public Option() {
    }

    public Option(boolean text) {
        this.text = text;
    }

    public Option(boolean text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public Option(boolean text, boolean isCorrect, String relationId) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.relationId = relationId;
    }

    @Override
    public String toString() {
        return "Option{" +
                "text=" + text +
                ", isCorrect=" + isCorrect +
                ", relationId='" + relationId + '\'' +
                '}';
    }
}

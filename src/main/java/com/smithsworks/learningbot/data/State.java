package com.smithsworks.learningbot.data;

import java.time.LocalDateTime;
import java.util.List;

public class State {

    public List<HandlingPoint> extra;

    public LocalDateTime creationDate;

    public State() {
    }

    public State(List<HandlingPoint> extra, LocalDateTime creationDate) {
        this.extra = extra;
        this.creationDate = creationDate;
    }

    public List<HandlingPoint> getExtra() {
        return extra;
    }

    public void setExtra(List<HandlingPoint> extra) {
        this.extra = extra;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "State{" +
                "extra=" + extra +
                ", creationDate=" + creationDate +
                '}';
    }
}


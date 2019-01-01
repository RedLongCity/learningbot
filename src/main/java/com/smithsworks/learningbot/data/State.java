package com.smithsworks.learningbot.data;

import java.time.LocalDateTime;

public class State {

    public String commandName;

    public LocalDateTime creationDate;

    public State() {
    }

    public State(String commandName) {
        this.commandName = commandName;
        this.creationDate = LocalDateTime.now();
    }

    public State(String commandName, LocalDateTime creationDate) {
        this.commandName = commandName;
        this.creationDate = creationDate;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
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
                "commandName='" + commandName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}


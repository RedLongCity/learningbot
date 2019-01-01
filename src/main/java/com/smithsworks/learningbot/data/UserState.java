package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

public class UserState {

    @Id
    public String id;

    public String userName;

    public Integer telegramId;

    public State currentState;

    public State previousState;

    public String locale;

    public UserState() {
    }

    public UserState(String userName, Integer telegramId, State currentState, State previousState, String locale) {
        this.userName = userName;
        this.telegramId = telegramId;
        this.currentState = currentState;
        this.previousState = previousState;
        this.locale = locale;
    }

    public UserState addStep(String commandName) {
        State state = new State(commandName);
        this.setPreviousState(this.getCurrentState());
        this.setCurrentState(state);
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "UserState{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", telegramId=" + telegramId +
                ", currentState=" + currentState +
                ", previousState=" + previousState +
                '}';
    }
}

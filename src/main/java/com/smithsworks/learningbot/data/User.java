package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    @Id
    public String id;

    public Integer telegramId;

    public String firstName;

    public String lastName;

    public String userName;

    public List<String> groupIds;

    public List<String> collectionsIds;

    public User() {
    }

    public User(Integer telegramId, String firstName, String lastName, String userName) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public List<String> getCollectionsIds() {
        return collectionsIds;
    }

    public void setCollectionsIds(List<String> collectionsIds) {
        this.collectionsIds = collectionsIds;
    }

    public void addGroupId(String groupId) {
        if (Objects.isNull(this.groupIds)) {
            this.groupIds = new ArrayList<>();
        }
        this.groupIds.add(groupId);
    }

    public void addGroupId(Group group) {
        if (!Objects.isNull(group)) {
            this.addGroupId(group.getId());
        }
    }

    public void addCollectionId(String collectionId) {
        if (Objects.isNull(this.collectionsIds)) {
            this.collectionsIds = new ArrayList<>();
        }
        this.collectionsIds.add(collectionId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", telegramId=" + telegramId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", groupIds=" + groupIds +
                ", collectionsIds=" + collectionsIds +
                '}';
    }
}

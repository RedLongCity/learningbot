package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {

    @Id
    public String id;

    public String title;

    public List<String> collectionsIds;

    public Group() {
    }

    public Group(String title) {
        this.title = title;
    }

    public Group(String title, List<String> collectionsIds) {
        this.title = title;
        this.collectionsIds = collectionsIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCollectionsIds() {
        return collectionsIds;
    }

    public void setCollectionsIds(List<String> collectionsIds) {
        this.collectionsIds = collectionsIds;
    }

    public void addCollectionId(String collectionId) {
        if (Objects.isNull(this.collectionsIds)) {
            this.collectionsIds = new ArrayList<>();
        }
        this.collectionsIds.add(collectionId);
    }

    public void addCollection(Collection collection) {
        if (!Objects.isNull(collection)) {
            this.addCollectionId(collection.getId());
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", collectionsIds=" + collectionsIds +
                '}';
    }
}

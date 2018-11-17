package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collection {

    @Id
    public String id;

    public List<String> translateIds;

    public Collection() {
    }

    public Collection(List<String> translateIds) {
        this.translateIds = translateIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTranslateIds() {
        return translateIds;
    }

    public void setTranslateIds(List<String> translateIds) {
        this.translateIds = translateIds;
    }

    public void addTranslateId(String id) {
        if (Objects.isNull(this.translateIds)) {
            this.translateIds = new ArrayList<>();
        }
        this.translateIds.add(id);
    }

    public void addTranslateId(Translate translate) {
        if (!Objects.isNull(translate)) {
            this.addTranslateId(translate.getId());
        }
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id='" + id + '\'' +
                ", translateIds=" + translateIds +
                '}';
    }
}

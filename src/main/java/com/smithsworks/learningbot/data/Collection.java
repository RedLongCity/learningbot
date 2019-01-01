package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collection {

    @Id
    public String id;

    public List<String> relationsIds;

    public Collection() {
    }

    public Collection(List<String> relationsIds) {
        this.relationsIds = relationsIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRelationsIds() {
        return relationsIds;
    }

    public void setRelationsIds(List<String> relationsIds) {
        this.relationsIds = relationsIds;
    }

    public void addTranslateId(String id) {
        if (Objects.isNull(this.relationsIds)) {
            this.relationsIds = new ArrayList<>();
        }
        this.relationsIds.add(id);
    }

    public void addTranslateId(Relation relation) {
        if (!Objects.isNull(relation)) {
            this.addTranslateId(relation.getId());
        }
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id='" + id + '\'' +
                ", relationsIds=" + relationsIds +
                '}';
    }
}

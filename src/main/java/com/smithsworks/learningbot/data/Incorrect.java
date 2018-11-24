package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Incorrect {

    @Id
    public String id;

    public String rusWordId;

    public List<String> realationIds;

    public Incorrect() {
    }

    public Incorrect(String rusWordId) {
        this.rusWordId = rusWordId;
    }

    public Incorrect(String rusWordId, List<String> realationIds) {
        this.rusWordId = rusWordId;
        this.realationIds = realationIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRusWordId() {
        return rusWordId;
    }

    public void setRusWordId(String rusWordId) {
        this.rusWordId = rusWordId;
    }

    public List<String> getRealationIds() {
        return realationIds;
    }

    public void setRealationIds(List<String> realationIds) {
        this.realationIds = realationIds;
    }

    public void addRelationId(String relationId) {
        if (Objects.isNull(this.realationIds)) {
            this.realationIds = new ArrayList<>();
        }
        this.realationIds.add(relationId);
    }

    public Incorrect addRelation(Relation relation) {
        if (!Objects.isNull(relation)) {
            this.addRelationId(relation.getId());
        }
        return this;
    }

    @Override
    public String toString() {
        return "Incorrect{" +
                "id='" + id + '\'' +
                ", rusWordId='" + rusWordId + '\'' +
                ", realationIds=" + realationIds +
                '}';
    }
}

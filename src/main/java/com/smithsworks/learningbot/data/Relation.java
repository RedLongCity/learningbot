package com.smithsworks.learningbot.data;

import org.springframework.data.annotation.Id;

public class Relation {

    @Id
    public String id;

    public String engWordId;

    public String rusWordId;

    public Integer weight;

    public Relation() {
    }

    public Relation(String engWordId) {
        this.engWordId = engWordId;
        this.weight = 0;
    }

    public Relation(String engWordId, Integer weight) {
        this.engWordId = engWordId;
        this.weight = weight;
    }

    public Relation(String engWordId, String rusWordId, Integer weight) {
        this.engWordId = engWordId;
        this.rusWordId = rusWordId;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngWordId() {
        return engWordId;
    }

    public void setEngWordId(String engWordId) {
        this.engWordId = engWordId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void incrementWeigh() {
       this.weight++;
    }

    public void decrementWeight() {
        this.weight--;
    }

    public String getRusWordId() {
        return rusWordId;
    }

    public void setRusWordId(String rusWordId) {
        this.rusWordId = rusWordId;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id='" + id + '\'' +
                ", engWordId='" + engWordId + '\'' +
                ", rusWordId='" + rusWordId + '\'' +
                ", weight=" + weight +
                '}';
    }
}

package com.smithsworks.learningbot.data;

public class HandlingPoint {

    public String handlerName;

    public String methodName;

    public String value;

    public HandlingPoint() {
    }

    public HandlingPoint(String handlerName, String methodName, String value) {
        this.handlerName = handlerName;
        this.methodName = methodName;
        this.value = value;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HandlingPoint{" +
                "handlerName='" + handlerName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

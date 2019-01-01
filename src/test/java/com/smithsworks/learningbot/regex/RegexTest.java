package com.smithsworks.learningbot.regex;

import com.smithsworks.learningbot.handler.NewWordUpdateHandler;
import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

    private final String testRusWord = "Тест";
    private final String testEngWord = "Test";

    @Test
    public void test() {
        Assert.assertTrue(NewWordUpdateHandler.engPattern.matcher(testEngWord).matches());
        Assert.assertFalse(NewWordUpdateHandler.engPattern.matcher(testRusWord).matches());
        Assert.assertTrue(NewWordUpdateHandler.rusPattern.matcher(testRusWord).matches());
        Assert.assertFalse(NewWordUpdateHandler.rusPattern.matcher(testEngWord).matches());
    }

}

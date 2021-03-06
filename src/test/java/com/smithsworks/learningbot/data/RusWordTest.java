package com.smithsworks.learningbot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RusWordTest {

    private static final String TEST1 = "ТЕСТ1";
    private static final String TEST2 = "ТЕСТ2";

    @Autowired
    private RusWordRepository repository;

    @Test
    public void saveTest() {
        RusWord test1 = repository.save(new RusWord(TEST1));
        RusWord test2 = repository.save(new RusWord(TEST2));
        Assert.assertFalse(Objects.isNull(test1));
        Assert.assertFalse(Objects.isNull(test2));
        repository.delete(test1);
        repository.delete(test2);
        test1 = repository.findByWord(TEST1);
        test2 = repository.findByWord(TEST2);
        Assert.assertTrue(Objects.isNull(test1));
        Assert.assertTrue(Objects.isNull(test2));
    }
}

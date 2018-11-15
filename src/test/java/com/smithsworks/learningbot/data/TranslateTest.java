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
public class TranslateTest {

    private static final String TEST1 = "TEST1";
    private static final String TEST2 = "TEST2";
    private static final String TEST1RUS = "ТЕСТ1";
    private static final String TEST2RUS = "ТЕСТ2";

    @Autowired
    private TranslateRepository repository;

    @Autowired
    private EngWordRepository engWordRepository;

    @Autowired
    private RusWordRepository rusWordRepository;

    @Test
    public void saveTest() {
        EngWord test1 = engWordRepository.save(new EngWord(TEST1));
        EngWord test2 = engWordRepository.save(new EngWord(TEST2));
        RusWord test1Rus = rusWordRepository.save(new RusWord(TEST1RUS));
        RusWord test2Rus = rusWordRepository.save(new RusWord(TEST2RUS));
        Translate translate1 = new Translate(test1);
        Translate translate2 = new Translate(test2);
        translate1.addRusWord(test1Rus);
        translate2.addRusWord(test2Rus);
        translate1 = repository.save(translate1);
        translate2 = repository.save(translate2);
        Assert.assertFalse(Objects.isNull(translate1));
        Assert.assertFalse(Objects.isNull(translate2));
        repository.delete(translate1);
        repository.delete(translate2);
        translate1 = repository.findByEngWordId(test1.getId());
        translate2 = repository.findByEngWordId(test2.getId());
        Assert.assertTrue(Objects.isNull(translate1));
        Assert.assertTrue(Objects.isNull(translate2));
    }
}

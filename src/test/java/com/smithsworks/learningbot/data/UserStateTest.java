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
public class UserStateTest {

    private static final String USERNAME = "USERNAME";
    private static final String TELEGRAMID = "TELEGRAMID";
    private static final String HANDLERID = "HANDLERID";


    @Autowired
    private UserStateRepository repository;

    @Test
    public void test() {
//        UserState state = repository.save(
////                new UserState(USERNAME, TELEGRAMID)
////                        .updateState(new State(HANDLERID))
////                        .updateState(new State(HANDLERID))
//        );
//        Assert.assertFalse(Objects.isNull(state));
//        repository.delete(state);
//        Assert.assertTrue(Objects.isNull(repository.findById(state.getId()).orElse(null)));
    }
}

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
public class UserTest {

    private static final String USER_FIRST_NAME = "USER_FIRST_NAME";
    private static final String USER_SECOND_NAME = "USER_SECOND_NAME";
    private static final String USER_NAME = "USER_NAME";
    private static final Integer TELEGRAM_ID = 0;

    @Autowired
    private UserRepository repository;

    @Test
    public void saveTest() {
        User user = repository.save(new User(
                TELEGRAM_ID,
                USER_FIRST_NAME,
                USER_SECOND_NAME,
                USER_NAME
        ));
        Assert.assertFalse(Objects.isNull(user));
        Assert.assertTrue(!Objects.isNull(repository.findByTelegramId(TELEGRAM_ID)));
        Assert.assertTrue(!Objects.isNull(repository.findByUserName(USER_NAME)));
        repository.delete(user);
        Assert.assertTrue(Objects.isNull(repository.findByTelegramId(TELEGRAM_ID)));
    }
}

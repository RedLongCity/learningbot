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
public class GroupTest {

    private static final String TITLE = "TITLE";

    @Autowired
    private GroupRepository repository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Test
    public void saveTest() {
        Group group = new Group(TITLE);
        Collection collection = collectionRepository.save(new Collection());
        group.addCollection(collection);
        group = repository.save(group);
        Assert.assertFalse(Objects.isNull(group));
        Assert.assertFalse(Objects.isNull(repository.findByTitle(TITLE)));
        Assert.assertFalse(Objects.isNull(repository.findByCollectionsIdsContains(collection.getId())));
        repository.delete(group);
        Assert.assertTrue(Objects.isNull(repository.findByTitle(TITLE)));

    }
}

package com.smithsworks.learningbot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RelationTest {

    private static final String ENGWORDIDFIRST = "ENGWORDIDFIRST";

    @Autowired
    private RelationRepository relationRepository;

    @Test
    public void saveTest() {
        List<Relation> relations = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            relations.add(relationRepository.save(new Relation(ENGWORDIDFIRST, i)));
        }
        List<Relation> list = relationRepository.findTop3RelationsByEngWordId(ENGWORDIDFIRST);
        Assert.assertFalse(CollectionUtils.isEmpty(list));
        relations.forEach(relation -> relationRepository.delete(relation));
        Assert.assertTrue(CollectionUtils.isEmpty(
                relationRepository.findTop3RelationsByEngWordId(ENGWORDIDFIRST)));
    }
}

package com.smithsworks.learningbot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IncorrectTest {

    private static final String ENGWORDID = "ENGWORDID";
    private static final List<String> rusIds = Arrays.asList("RUSWORDID1", "RUSWORDID2", "RUSWORDID3", "RUSWORDID4", "RUSWORDID5");

    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private IncorrectRepository incorrectRepository;

    @Test
    public void test() {
        List<Relation> relations = new ArrayList<>();
        List<Incorrect> incorrects = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            String rusWordId = rusIds.get(i);
            Relation relation = relationRepository.save(new Relation(ENGWORDID, rusWordId, i));
            incorrects.add(incorrectRepository.save(new Incorrect(rusWordId).addRelation(relation)));
            relations.add(relation);
        }
        List<Relation> relationsEntity = relationRepository.findTop3ByEngWordIdOrderByWeightDesc(ENGWORDID);
        List<Incorrect> incorrectsEntity = incorrectRepository.findIncorrectsByRealationIdsIn(
                relationsEntity.stream().map(Relation::getId).collect(Collectors.toList()));
        Assert.assertFalse(CollectionUtils.isEmpty(incorrectsEntity) && incorrectsEntity.size() == 3);
        relations.forEach(relation -> relationRepository.delete(relation));
        incorrects.forEach(incorrect -> incorrectRepository.delete(incorrect));
        Assert.assertTrue(CollectionUtils.isEmpty(relationRepository.findTop3ByEngWordIdOrderByWeightDesc(ENGWORDID)));
        Assert.assertTrue(CollectionUtils.isEmpty(incorrectRepository.findIncorrectsByRealationIdsIn(
                relationsEntity.stream().map(Relation::getId).collect(Collectors.toList()))));
    }


}

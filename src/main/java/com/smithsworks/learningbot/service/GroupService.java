package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.Group;
import com.smithsworks.learningbot.data.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group saveOrGet(Group group) {
        Group entity = groupRepository.findByTitle(group.getTitle());
        if (Objects.isNull(entity))
            return groupRepository.save(group);
        else
            return entity;
    }

    public Group findByTitle(Group group) {
        return groupRepository.findByTitle(group.getTitle());
    }

    public Group update(Group group) {
        return groupRepository.save(group);
    }

}

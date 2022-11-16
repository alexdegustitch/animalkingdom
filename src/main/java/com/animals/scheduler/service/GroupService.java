package com.animals.scheduler.service;

import com.animals.scheduler.model.GroupModel;
import com.animals.scheduler.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupModel findAnimalByStage(int animal, int stage){
        return groupRepository.findAnimalByStage(animal, stage);
    }

    public List<GroupModel> groupsForStage(int stage, int number){
        return groupRepository.groupsForStage(stage, number);
    }

    public GroupModel saveGroup(GroupModel groupModel){
        return groupRepository.save(groupModel);
    }

    public GroupModel updatePoints(GroupModel groupModel){
        return groupRepository.saveAndFlush(groupModel);
    }
}

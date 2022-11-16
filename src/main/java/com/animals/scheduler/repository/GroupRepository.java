package com.animals.scheduler.repository;


import com.animals.scheduler.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Integer> {

    @Query(value = "SELECT * FROM group_table g WHERE g.animal = :animal AND g.stage = :stage", nativeQuery = true)
    GroupModel findAnimalByStage(@Param("animal") Integer animal, @Param("stage") Integer stage);

    @Query(value = "SELECT * FROM group_table gt WHERE gt.stage = :stage and gt.group_number = :number ORDER BY gt.points DESC, gt.animal ASC", nativeQuery = true)
    List<GroupModel> groupsForStage(@Param("stage") Integer stage, @Param("number") Integer number);


}

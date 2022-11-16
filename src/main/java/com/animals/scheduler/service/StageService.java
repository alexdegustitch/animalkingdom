package com.animals.scheduler.service;


import com.animals.scheduler.model.StageModel;
import com.animals.scheduler.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public StageModel getActiveStageForCompetition(int id_competition){
        return stageRepository.getActiveStageForCompetition(id_competition);
    }

    public List<StageModel> getStagesForCompetition(int id_competition){
        return stageRepository.getStagesForCompetition(id_competition);
    }

    public StageModel saveStage(StageModel stageModel){
        return stageRepository.save(stageModel);
    }

    public StageModel getStageForCompetitionByStageType(int id_competition, int stage){
        return stageRepository.getStageForCompetitionByStageType(id_competition, stage);
    }
}

package com.animals.scheduler.jobs;

import com.animals.scheduler.model.StageModel;
import com.animals.scheduler.model.TournamentModel;
import com.animals.scheduler.service.StageService;
import com.animals.scheduler.service.TournamentService;
import com.animals.scheduler.utils.ConnectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StageJob implements Job {

    @Autowired
    private StageService stageService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("STAGE JOB STARTED " + jobExecutionContext.getFireTime());
        LocalDateTime now = LocalDateTime.now();
        List<TournamentModel> notFinishedTours = tournamentService.getNotFinishedTournaments(now);
        if(notFinishedTours == null) {
            return;
        }
        for(TournamentModel tournamentModel: notFinishedTours) {
            List<StageModel> stages = stageService.getStagesForCompetition(tournamentModel.getId_competition());
            if(stages != null){
                for(StageModel currStage: stages){
                    LocalDateTime startDate = currStage.getStart_date();
                    LocalDateTime endDate = currStage.getEnd_date();

                    if(now.isAfter(endDate) && currStage.getActive() == 1)
                    {
                        currStage.setActive(0);
                        stageService.saveStage(currStage);
                        if(currStage.getStage_type() == stages.size()){
                            tournamentModel.setActive(0);
                            tournamentService.saveTournament(tournamentModel);
                        }
                        connectionUtils.makeGroups(currStage);
                    }

                    if(now.isAfter(startDate) && now.isBefore(endDate) && currStage.getActive() == 0){
                        currStage.setActive(1);
                        stageService.saveStage(currStage);
                        if(currStage.getStage_type() == 1) {
                            tournamentModel.setActive(1);
                        }
                        tournamentModel.setActive_stage(currStage.getStage_type());
                        tournamentService.saveTournament(tournamentModel);
                    }

                }
            }
        }

    }
}

package com.animals.scheduler.service;

import com.animals.scheduler.model.TournamentModel;
import com.animals.scheduler.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<TournamentModel> getAllActiveTournaments(){
        return tournamentRepository.getActiveTournaments();
    }

    public List<TournamentModel> getNotFinishedTournaments(LocalDateTime now) {
        return tournamentRepository.getNotFinishedTournaments(now);
    }

    public TournamentModel saveTournament(TournamentModel tournamentModel){
        return tournamentRepository.save(tournamentModel);
    }
}

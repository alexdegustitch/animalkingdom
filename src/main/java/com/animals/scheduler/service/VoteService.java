package com.animals.scheduler.service;


import com.animals.scheduler.model.VoteModel;
import com.animals.scheduler.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public VoteModel getVote(int wanted_animal, String user, int stage){
        return voteRepository.getVote(wanted_animal, user, stage);
    }

    public VoteModel saveVote(VoteModel voteModel){
        return voteRepository.saveAndFlush(voteModel);
    }
}

package com.animals.scheduler.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
public class VoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_vote;

    @Column
    private int stage;

    @Column
    private int offered_animal;

    @Column
    private int wanted_animal;

    @Column
    private String username;

    @Column
    private LocalDateTime voting_time;

    public VoteModel(){

    }

    public VoteModel(int id_vote, int stage, int offered_animal, int wanted_animal, String username, LocalDateTime voting_time) {
        this.id_vote = id_vote;
        this.stage = stage;
        this.offered_animal = offered_animal;
        this.wanted_animal = wanted_animal;
        this.username = username;
        this.voting_time = voting_time;
    }

    public int getId_vote() {
        return id_vote;
    }

    public void setId_vote(int id_vote) {
        this.id_vote = id_vote;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getOffered_animal() {
        return offered_animal;
    }

    public void setOffered_animal(int offered_animal) {
        this.offered_animal = offered_animal;
    }

    public int getWanted_animal() {
        return wanted_animal;
    }

    public void setWanted_animal(int wanted_animal) {
        this.wanted_animal = wanted_animal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getVoting_time() {
        return voting_time;
    }

    public void setVoting_time(LocalDateTime voting_time) {
        this.voting_time = voting_time;
    }
}

package com.animals.scheduler.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "competition")
public class TournamentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_competition;

    @Column
    private String name;

    @Column
    private LocalDateTime start_date;

    @Column
    private LocalDateTime end_date;

    @Column
    private int active;

    @Column
    private int active_stage;

    public TournamentModel(){

    }

    public TournamentModel(int id_competition, String name, LocalDateTime start_date, LocalDateTime end_date, int active, int active_stage) {
        this.id_competition = id_competition;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.active = active;
        this.active_stage = active_stage;
    }

    public int getId_competition() {
        return id_competition;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getActive_stage() {
        return active_stage;
    }

    public void setActive_stage(int active_stage) {
        this.active_stage = active_stage;
    }
}

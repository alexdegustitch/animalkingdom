package com.animals.scheduler.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stage")
public class StageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_stage;

    @Column
    private int competition;

    @Column
    private int stage_type;

    @Column
    private LocalDateTime start_date;

    @Column
    private LocalDateTime end_date;

    @Column
    private int active;

    private StageModel(){

    }

    public StageModel(int id_stage, int competition, int stage_type, LocalDateTime start_date, LocalDateTime end_date, int active) {
        this.id_stage = id_stage;
        this.competition = competition;
        this.stage_type = stage_type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.active = active;
    }

    public int getId_stage() {
        return id_stage;
    }

    public void setId_stage(int id_stage) {
        this.id_stage = id_stage;
    }

    public int getCompetition() {
        return competition;
    }

    public void setCompetition(int competition) {
        this.competition = competition;
    }

    public int getStage_type() {
        return stage_type;
    }

    public void setStage_type(int stage_type) {
        this.stage_type = stage_type;
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
}

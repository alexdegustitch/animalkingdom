package com.animals.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "group_table")
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_group;

    @Column
    private int stage;

    @Column
    private int animal;

    @Column
    private int points;

    @Column
    private int passed;

    @Column
    private int group_number;

    @Column
    private int active = 1;

    public GroupModel(){

    }

    public GroupModel(int id_group, int stage, int animal, int points, int passed, int group_number, int active) {
        this.id_group = id_group;
        this.stage = stage;
        this.animal = animal;
        this.points = points;
        this.passed = passed;
        this.group_number = group_number;
        this.active = active;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getGroup_number() {
        return group_number;
    }

    public void setGroup_number(int group_number) {
        this.group_number = group_number;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

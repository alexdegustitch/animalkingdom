package com.animals.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "animal")
public class AnimalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_animal;

    @Column
    private String name;

    @Column
    private String binomial_name;

    @Column
    private String description;

    @Column
    private String lifespan;

    @Column
    private String weight;

    @Column
    private String size;

    @Column
    private String color;

    @Column
    private String temperament;

    @Column
    private String activity;

    @Column
    private String speed;

    @Column
    private String calves_number;

    @Column
    private int trophic_level;

    @Column
    private String gestation_period;

    @Column
    private String kingdom;

    @Column
    private String phylum;

    @Column
    private String class_name;

    @Column
    private String order_name;

    @Column
    private String family;

    @Column
    private String genus;

    @Column
    private String habitat;

    @Column
    private String population;

    @Column
    private String conservation_status;

    @Column
    private String territory;

    @Column
    private String baby_name;

    @Column
    private int number;

    public AnimalModel() {
    }

    public AnimalModel(int id_animal, String name, String binomial_name, String description, String lifespan, String color, String temperament, String activity, String weight, String size, String speed, String calves_number, int trophic_level, String gestation_period, String kingdom, String phylum, String class_name, String order_name, String family, String genus, String habitat, String population, String conservation_status, String territory, int number, String baby_name) {
        this.id_animal = id_animal;
        this.name = name;
        this.binomial_name = binomial_name;
        this.description = description;
        this.lifespan = lifespan;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.temperament = temperament;
        this.activity = activity;
        this.speed = speed;
        this.calves_number = calves_number;
        this.trophic_level = trophic_level;
        this.gestation_period = gestation_period;
        this.kingdom = kingdom;
        this.phylum = phylum;
        this.class_name = class_name;
        this.order_name = order_name;
        this.family = family;
        this.genus = genus;
        this.habitat = habitat;
        this.population = population;
        this.conservation_status = conservation_status;
        this.territory = territory;
        this.number = number;
        this.baby_name = baby_name;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBinomial_name() {
        return binomial_name;
    }

    public void setBinomial_name(String binomial_name) {
        this.binomial_name = binomial_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCalves_number() {
        return calves_number;
    }

    public void setCalves_number(String calves_number) {
        this.calves_number = calves_number;
    }

    public int getTrophic_level() {
        return trophic_level;
    }

    public void setTrophic_level(int trophic_level) {
        this.trophic_level = trophic_level;
    }

    public String getGestation_period() {
        return gestation_period;
    }

    public void setGestation_period(String gestation_period) {
        this.gestation_period = gestation_period;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getConservation_status() {
        return conservation_status;
    }

    public void setConservation_status(String conservation_status) {
        this.conservation_status = conservation_status;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBaby_name() {
        return baby_name;
    }

    public void setBaby_name(String baby_name) {
        this.baby_name = baby_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}

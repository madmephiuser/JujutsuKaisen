package com.mycompany.jujutsukaisen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.TechniqueType;
import jakarta.persistence.*;

@Entity
@Table(name = "techniques")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Technique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Enumerated(EnumType.STRING)
    private TechniqueType type;
    
    private String owner; 
    private long damage;
    
    @ManyToOne
    @JoinColumn(name = "mission_id")
    @JsonIgnore
    private Mission mission;

    public Technique() {}

    public String getName() {
        return name; 
    }
    public void setName(String name) {
        this.name = name; 
    }

    public TechniqueType getType() {
        return type; 
    }
    public void setType(TechniqueType type) {
        this.type = type; 
    }

    public String getOwner() {
        return owner; 
    }
    public void setOwner(String owner) {
        this.owner = owner; 
    }

    public long getDamage() {
        return damage; 
    }
    public void setDamage(long damage) {
        this.damage = damage; 
    }
    
    public void setMission(Mission mission) {
        this.mission = mission; 
    }
}
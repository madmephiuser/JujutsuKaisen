package com.mycompany.jujutsukaisen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.SorcererRank;
import jakarta.persistence.*;

@Entity
@Table(name = "sorcerers")
public class Sorcerer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private SorcererRank rank;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    @JsonIgnore
    private Mission mission;
    
    public Sorcerer() {
    
    }  
    public Sorcerer(String name) {
        this.name = name;
    }

    public String getName() {
        return name; 
    }
    public void setName(String name) {
        this.name = name; 
    }  
    public SorcererRank getRank() {
        return rank; 
    }
    public void setRank(SorcererRank rank) {
        this.rank = rank; 
    }
    public void setMission(Mission mission) {
        this.mission = mission; 
    }
    public Mission getMission() {
        return mission; 
    }
}

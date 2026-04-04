package com.mycompany.jujutsukaisen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.SorcererRank;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Sorcerer {
    private String name;
    private SorcererRank rank;
    
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
}

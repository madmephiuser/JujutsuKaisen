package com.mycompany.jujutsukaisen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sorcerer {
    private String name;
    private String rank;
    
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

    public String getRank() {
        return rank; 
    }
    public void setRank(String rank) {
        this.rank = rank; 
    }
}

package com.mycompany.jujutsukaisen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class Technique {
    private String name;
    private String type;
    private Sorcerer owner; 
    private long damage;

    public Technique() {}

    public String getName() {
        return name; 
    }
    public void setName(String name) {
        this.name = name; 
    }

    public String getType() {
        return type; 
    }
    public void setType(String type) {
        this.type = type; 
    }

    public Sorcerer getOwner() {
        return owner; 
    }
    public void setOwner(Sorcerer owner) {
        this.owner = owner; 
    }

    public long getDamage() {
        return damage; 
    }
    public void setDamage(long damage) {
        this.damage = damage; 
    }
}
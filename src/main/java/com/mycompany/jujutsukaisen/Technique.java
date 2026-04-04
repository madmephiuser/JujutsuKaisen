package com.mycompany.jujutsukaisen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.TechniqueType;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Technique {
    private String name;
    private TechniqueType type;
    private Sorcerer owner; 
    private long damage;

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
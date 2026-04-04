package com.mycompany.jujutsukaisen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.ThreatLevel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Curse {
    private String name;
    private ThreatLevel threatLevel;

    public Curse() {}

    public String getName() {
        return name; 
    }
    public void setName(String name) {
        this.name = name; 
    }

    public ThreatLevel getThreatLevel() {
        return threatLevel; 
    }
    public void setThreatLevel(ThreatLevel threatLevel) {
        this.threatLevel = threatLevel; 
    }
}
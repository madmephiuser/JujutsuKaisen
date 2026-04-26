package com.mycompany.jujutsukaisen;

import enums.ThreatLevel;
import jakarta.persistence.*;

@Embeddable
public class Curse {
    @Column(name = "curse_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "curse_threat_level")
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
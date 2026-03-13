package com.mycompany.jujutsukaisen;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;
    private Curse curse;

    @JacksonXmlElementWrapper(localName = "sorcerers")
    @JacksonXmlProperty(localName = "sorcerer")
    private List<Sorcerer> sorcerers;
    @JacksonXmlElementWrapper(localName = "techniques")
    @JacksonXmlProperty(localName = "technique")
    private List<Technique> techniques;
    @JsonAlias({"comment", "note"})
    private String comment;

    public Mission() {}

    public String getMissionId() {
        return missionId; 
    }
    public void setMissionId(String missionId) {
        this.missionId = missionId; 
    }

    public String getDate() {
        return date; 
    }
    public void setDate(String date) {
        this.date = date; 
    }
    public String getLocation() {
        return location;
    } 
    public void setLocation(String location) {
        this.location = location; 
    }

    public String getOutcome() {
        return outcome; 
    }
    public void setOutcome(String outcome) {
        this.outcome = outcome; 
    }
    public long getDamageCost() {
        return damageCost; 
    }
    public void setDamageCost(long damageCost) {
        this.damageCost = damageCost; 
    }

    public Curse getCurse() {
        return curse; 
    }
    public void setCurse(Curse curse) {
        this.curse = curse; 
    }
    public List<Sorcerer> getSorcerers() {
        return sorcerers; 
    }
    public void setSorcerers(List<Sorcerer> sorcerers) {
        this.sorcerers = sorcerers; 
    }

    public List<Technique> getTechniques() {
        return techniques; 
    }
    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques; 
    }

    public String getComment() {
        return comment; 
    }
    public void setComment(String comment) {
        this.comment = comment; 
    }
}
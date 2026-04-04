package com.mycompany.jujutsukaisen;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import enums.MissionOutcome;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mission {
    private String missionId;
    private String date;
    private String location;
    private MissionOutcome outcome;
    private long damageCost;
    private Curse curse;

    @JacksonXmlElementWrapper(localName = "sorcerers")
    @JacksonXmlProperty(localName = "sorcerer")
    private List<Sorcerer> sorcerers;
    @JacksonXmlElementWrapper(localName = "techniques")
    @JacksonXmlProperty(localName = "technique")
    private List<Technique> techniques;
    private String notes;
    
    private EconomicAssessment economicAssessment;
    private CivilianImpact civilianImpact;
    private EnemyActivity enemyActivity;
    private EnvironmentConditions environmentConditions;
    private List<OperationTimeline> operationTimeline;
    private List<String> operationTags;
    private List<String> supportUnits;
    private List<String> recommendations;
    private List<String> artifactsRecovered;
    private List<String> evacuationZones;
    private List<String> statusEffects;
    
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

    public MissionOutcome getOutcome() {
        return outcome; 
    }
    public void setOutcome(MissionOutcome outcome) {
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

    public String getNotes() {
        return notes; 
    }
    public void setComment(String notes) {
        this.notes = notes; 
    }
    public EconomicAssessment getEconomicAssessment() {
        return economicAssessment; 
    }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) {
        this.economicAssessment = economicAssessment; 
    }

    public CivilianImpact getCivilianImpact() {
        return civilianImpact; 
    }
    public void setCivilianImpact(CivilianImpact civilianImpact) {
        this.civilianImpact = civilianImpact; 
    }

    public EnemyActivity getEnemyActivity() {
        return enemyActivity; 
    }
    public void setEnemyActivity(EnemyActivity enemyActivity) {
        this.enemyActivity = enemyActivity; 
    }

    public EnvironmentConditions getEnvironmentConditions() {
        return environmentConditions; 
    }
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) {
        this.environmentConditions = environmentConditions; 
    }
    public List<OperationTimeline> getOperationTimeline() {
        return operationTimeline; 
    }
    public void setOperationTimeline(List<OperationTimeline> operationTimeline) {
        this.operationTimeline = operationTimeline; 
    }
    public List<String> getOperationTags() {
        return operationTags; 
    }
    public void setOperationTags(List<String> operationTags) {
        this.operationTags = operationTags;
    }

    public List<String> getSupportUnits() {
        return supportUnits;
    }
    public void setSupportUnits(List<String> supportUnits) {
        this.supportUnits = supportUnits;
    }
    public List<String> getRecommendations() {
        return recommendations; }
    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations; 
    }

    public List<String> getArtifactsRecovered() {
        return artifactsRecovered; 
    }
    public void setArtifactsRecovered(List<String> artifactsRecovered) { 
        this.artifactsRecovered = artifactsRecovered; 
    }

    public List<String> getEvacuationZones() { 
        return evacuationZones; 
    }
    public void setEvacuationZones(List<String> evacuationZones) { 
        this.evacuationZones = evacuationZones; 
    }

    public List<String> getStatusEffects() {
        return statusEffects; 
    }
    public void setStatusEffects(List<String> statusEffects) {
        this.statusEffects = statusEffects; 
    }
}
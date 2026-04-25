package com.mycompany.jujutsukaisen;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.MissionOutcome;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mission {
    private String missionId;
    private String date;    
    private String location;
    private MissionOutcome outcome;
    private Long damageCost;
    
    private Curse curse = new Curse();
    private EconomicAssessment economicAssessment = new EconomicAssessment();
    private CivilianImpact civilianImpact = new CivilianImpact();
    private EnemyActivity enemyActivity = new EnemyActivity();
    private EnvironmentConditions environmentConditions = new EnvironmentConditions();
    private List<Sorcerer> sorcerers = new ArrayList<>();
    private List<Technique> techniques = new ArrayList<>();
    private List<OperationTimeline> operationTimeline = new ArrayList<>();
    private List<String> operationTags = new ArrayList<>();
    private List<String> supportUnits = new ArrayList<>();
    private List<String> recommendations = new ArrayList<>();
    private List<String> artifactsRecovered = new ArrayList<>();
    private List<String> evacuationZones = new ArrayList<>();
    private List<String> statusEffects = new ArrayList<>();
    private String notes;

    public Mission() {}
    
    public void addSorcerer(Sorcerer s) {
        this.sorcerers.add(s); 
    }
    public void addTechnique(Technique t) {
        this.techniques.add(t); 
    }
    public void addTimeline(OperationTimeline ot) {
        this.operationTimeline.add(ot); 
    }
    public void addTag(String tag) {
        this.operationTags.add(tag); 
    }
    public void addSupport(String support) {
        this.supportUnits.add(support); 
    }
    public void addArtifact(String artifact) {
        this.artifactsRecovered.add(artifact); 
    }
    public void addRecommendation(String rec) {
        this.recommendations.add(rec); 
    }
    public void addEvacuationZone(String zone) {
        this.evacuationZones.add(zone); 
    }
    public void addStatusEffect(String effect) {
        this.statusEffects.add(effect); 
    }

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
    public Long getDamageCost() {
        return damageCost; 
    }
    public void setDamageCost(Long damageCost) {
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
    public void setNotes(String notes) {
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
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
    private Curse curse;
    private List<Sorcerer> sorcerers;
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

    private Mission(Builder builder) {
        this.missionId = builder.missionId;
        this.date = builder.date;
        this.location = builder.location;
        this.outcome = builder.outcome;
        this.damageCost = builder.damageCost;
        this.curse = builder.curse;
        this.sorcerers = builder.sorcerers;
        this.techniques = builder.techniques;
        this.economicAssessment = builder.economicAssessment;
        this.civilianImpact = builder.civilianImpact;
        this.enemyActivity = builder.enemyActivity;
        this.environmentConditions = builder.environmentConditions;
        this.operationTimeline = builder.operationTimeline;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String missionId;
        private String date;
        private String location;
        private MissionOutcome outcome;
        private Long damageCost;
        private Curse curse;
        private List<Sorcerer> sorcerers = new ArrayList<>();
        private List<Technique> techniques = new ArrayList<>();
        private EconomicAssessment economicAssessment;
        private CivilianImpact civilianImpact;
        private EnemyActivity enemyActivity;
        private EnvironmentConditions environmentConditions;
        private List<OperationTimeline> operationTimeline = new ArrayList<>();
        private List<String> operationTags = new ArrayList<>();
        private List<String> supportUnits = new ArrayList<>();
        private List<String> recommendations = new ArrayList<>();
        private List<String> artifactsRecovered = new ArrayList<>();
        private List<String> evacuationZones = new ArrayList<>();
        private List<String> statusEffects = new ArrayList<>();
        private String notes;

        public Builder missionId(String id) { this.missionId = id; return this; }
        public Builder date(String d) { this.date = d; return this; }
        public Builder location(String l) { this.location = l; return this; }
        public Builder outcome(MissionOutcome o) { this.outcome = o; return this; }
        public Builder damageCost(Long c) { this.damageCost = c; return this; }
        public Builder notes(String n) { this.notes = n; return this; }

        public Builder addSorcerer(Sorcerer s) { this.sorcerers.add(s); return this; }
        public Sorcerer getLastSorcerer() { return sorcerers.isEmpty() ? null : sorcerers.get(sorcerers.size() - 1); }

        public Builder addTechnique(Technique t) { this.techniques.add(t); return this; }
        public Technique getLastTechnique() { return techniques.isEmpty() ? null : techniques.get(techniques.size() - 1); }

        public Builder addTimeline(OperationTimeline ot) { this.operationTimeline.add(ot); return this; }
        public OperationTimeline getLastTimeline() { return operationTimeline.isEmpty() ? null : operationTimeline.get(operationTimeline.size() - 1); }

        public Builder curse(Curse c) { this.curse = c; return this; }
        public Curse getCurse() { if(this.curse == null) this.curse = new Curse(); return this.curse; }

        public Builder economicAssessment(EconomicAssessment ea) { this.economicAssessment = ea; return this; }
        public EconomicAssessment getEconomicAssessment() { if(this.economicAssessment == null) this.economicAssessment = new EconomicAssessment(); return this.economicAssessment; }

        public Builder civilianImpact(CivilianImpact ci) { this.civilianImpact = ci; return this; }
        public CivilianImpact getCivilianImpact() { if(this.civilianImpact == null) this.civilianImpact = new CivilianImpact(); return this.civilianImpact; }

        public Builder enemyActivity(EnemyActivity en) { this.enemyActivity = en; return this; }
        public EnemyActivity getEnemyActivity() { if(this.enemyActivity == null) this.enemyActivity = new EnemyActivity(); return this.enemyActivity; }

        public Builder environmentConditions(EnvironmentConditions ec) { this.environmentConditions = ec; return this; }
        public EnvironmentConditions getEnvironmentConditions() { if(this.environmentConditions == null) this.environmentConditions = new EnvironmentConditions(); return this.environmentConditions; }

        public Builder addTag(String tag) { this.operationTags.add(tag); return this; }
        public Builder addSupportUnit(String unit) { this.supportUnits.add(unit); return this; }
        public Builder addRecommendation(String rec) { this.recommendations.add(rec); return this; }
        public Builder addArtifact(String art) { this.artifactsRecovered.add(art); return this; }
        public Builder addEvacuationZone(String zone) { this.evacuationZones.add(zone); return this; }
        public Builder addStatusEffect(String effect) { this.statusEffects.add(effect); return this; }

        public Mission build() {
            return new Mission(this); 
        }
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
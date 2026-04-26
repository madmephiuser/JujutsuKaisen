package com.mycompany.jujutsukaisen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.MissionOutcome;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mission {

    @Id
    @Column(name = "mission_id")
    private String missionId;

    private String date;    
    private String location;

    @Enumerated(EnumType.STRING)
    private MissionOutcome outcome;

    private Long damageCost;

    @Embedded
    private Curse curse = new Curse();

    @Embedded
    private EconomicAssessment economicAssessment = new EconomicAssessment();

    @Embedded
    private CivilianImpact civilianImpact = new CivilianImpact();

    @Embedded
    private EnemyActivity enemyActivity = new EnemyActivity();

    @Embedded
    private EnvironmentConditions environmentConditions = new EnvironmentConditions();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sorcerer> sorcerers = new ArrayList<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Technique> techniques = new ArrayList<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationTimeline> operationTimeline = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mission_tags", joinColumns = @JoinColumn(name = "mission_id"))
    private List<String> operationTags = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "support_units", joinColumns = @JoinColumn(name = "mission_id"))
    private List<String> supportUnits = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "recommendations", joinColumns = @JoinColumn(name = "mission_id"))
    private List<String> recommendations = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "artifacts_recovered", joinColumns = @JoinColumn(name = "mission_id"))
    private List<String> artifactsRecovered = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "evacuation_zones", joinColumns = @JoinColumn(name = "mission_id"))
    private List<String> evacuationZones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "status_effects", joinColumns = @JoinColumn(name = "mission_id"))
    private List<String> statusEffects = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String notes;

    public Mission() {}

    
    public void addSorcerer(Sorcerer s) {
        this.sorcerers.add(s);
        s.setMission(this);
    }

    public void addTechnique(Technique t) {
        this.techniques.add(t);
        t.setMission(this);
    }

    public void addTimeline(OperationTimeline ot) {
        this.operationTimeline.add(ot);
        ot.setMission(this);
    }
    
    public void addTag(String tag) {
        this.operationTags.add(tag); 
    }
    public void addSupport(String support) { this.supportUnits.add(support); }
    public void addArtifact(String artifact) { this.artifactsRecovered.add(artifact); }
    public void addRecommendation(String rec) { this.recommendations.add(rec); }
    public void addEvacuationZone(String zone) { this.evacuationZones.add(zone); }
    public void addStatusEffect(String effect) { this.statusEffects.add(effect); }
    
    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public MissionOutcome getOutcome() { return outcome; }
    public void setOutcome(MissionOutcome outcome) { this.outcome = outcome; }
    public Long getDamageCost() { return damageCost; }
    public void setDamageCost(Long damageCost) { this.damageCost = damageCost; }
    public Curse getCurse() { return curse; }
    public void setCurse(Curse curse) { this.curse = curse; }
    public List<Sorcerer> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<Sorcerer> sorcerers) { this.sorcerers = sorcerers; }
    public List<Technique> getTechniques() { return techniques; }
    public void setTechniques(List<Technique> techniques) { this.techniques = techniques; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public EconomicAssessment getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) { this.economicAssessment = economicAssessment; }
    public CivilianImpact getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpact civilianImpact) { this.civilianImpact = civilianImpact; }
    public EnemyActivity getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivity enemyActivity) { this.enemyActivity = enemyActivity; }
    public EnvironmentConditions getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) { this.environmentConditions = environmentConditions; }
    public List<OperationTimeline> getOperationTimeline() { return operationTimeline; }
    public void setOperationTimeline(List<OperationTimeline> operationTimeline) { this.operationTimeline = operationTimeline; }
    public List<String> getOperationTags() { return operationTags; }
    public void setOperationTags(List<String> operationTags) { this.operationTags = operationTags; }
    public List<String> getSupportUnits() { return supportUnits; }
    public void setSupportUnits(List<String> supportUnits) { this.supportUnits = supportUnits; }
    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
    public List<String> getArtifactsRecovered() { return artifactsRecovered; }
    public void setArtifactsRecovered(List<String> artifactsRecovered) { this.artifactsRecovered = artifactsRecovered; }
    public List<String> getEvacuationZones() { return evacuationZones; }
    public void setEvacuationZones(List<String> evacuationZones) { this.evacuationZones = evacuationZones; }
    public List<String> getStatusEffects() { return statusEffects; }
    public void setStatusEffects(List<String> statusEffects) { this.statusEffects = statusEffects; }
}
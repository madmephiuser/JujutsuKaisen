
package com.mycompany.jujutsukaisen;

import enums.*;
import java.util.List;

public class MissionBuilder extends Builder {

    @Override
    public void buildBaseInfo(String key, String value) {
        switch (key) {
            case "missionId" -> mission.setMissionId(value);
            case "date" -> mission.setDate(value);
            case "location" -> mission.setLocation(value);
            case "outcome" -> mission.setOutcome(MissionOutcome.fromString(value));
            case "damageCost" -> mission.setDamageCost(parseLong(value));
        }
    }

    @Override
    public void buildCurse(String key, String value) {
        Curse c = mission.getCurse();
        if (key.equals("name")) c.setName(value);
        else if (key.equals("threatLevel")) c.setThreatLevel(ThreatLevel.fromString(value));
    }

    @Override
    public void buildSorcerer(String key, String value) {
        if (key.equals("name")) {
            mission.addSorcerer(new Sorcerer(value));
        } else if (key.equals("rank")) {
            List<Sorcerer> list = mission.getSorcerers();
            if (!list.isEmpty()) {
                list.get(list.size() - 1).setRank(SorcererRank.fromString(value));
            }
        }
    }

    @Override
    public void buildTechnique(String key, String value) {
        if (key.equals("name")) {
            Technique t = new Technique();
            t.setName(value);
            mission.addTechnique(t);
        } else {
            List<Technique> list = mission.getTechniques();
            if (list.isEmpty()) return;
            Technique last = list.get(list.size() - 1);
            switch (key) {
                case "type" -> last.setType(TechniqueType.fromString(value));
                case "owner" -> last.setOwner(value);
                case "damage" -> last.setDamage(parseLong(value));
            }
        }
    }

    @Override
    public void buildEconomic(String key, String value) {
        EconomicAssessment ec = mission.getEconomicAssessment();
        switch (key) {
            case "totalDamageCost" -> ec.setTotalDamageCost(parseDouble(value));
            case "infrastructureDamage" -> ec.setInfrastructureDamage(parseDouble(value));
            case "commercialDamage" -> ec.setCommercialDamage(parseDouble(value));
            case "transportDamage" -> ec.setTransportDamage(parseDouble(value));
            case "recoveryEstimateDays" -> ec.setRecoveryEstimateDays(Integer.parseInt(value));
            case "insuranceCovered" -> ec.setInsuranceCovered(Boolean.parseBoolean(value));
        }
    }

    @Override
    public void buildCivilian(String key, String value) {
        CivilianImpact ci = mission.getCivilianImpact();
        switch (key) {
            case "evacuated" -> ci.setEvacuated(Integer.valueOf(value));
            case "injured" -> ci.setInjured(Integer.parseInt(value));
            case "missing" -> ci.setMissing(Integer.parseInt(value));
            case "publicExposureRisk" -> ci.setPublicExposureRisk(value);
        }
    }

    @Override
    public void buildEnemy(String key, String value) {
        EnemyActivity en = mission.getEnemyActivity();
        switch (key) {
            case "behaviorType" -> en.setBehaviorType(value);
            case "mobility" -> en.setMobility(Mobility.fromString(value));
            case "escalationRisk" -> en.setEscalationRisk(RiskLevel.fromString(value));
            case "targetPriority" -> en.setTargetPriority(splitList(value));
            case "attackPatterns" -> en.setAttackPatterns(splitList(value));
        }
    }

    @Override
    public void buildEnvironment(String key, String value) {
        EnvironmentConditions env = mission.getEnvironmentConditions();
        switch (key) {
            case "weather" -> env.setWeather(value);
            case "timeOfDay" -> env.setTimeOfDay(value);
            case "visibility" -> env.setVisibility(Visibility.fromString(value));
            case "cursedEnergyDensity" -> env.setCursedEnergyDensity(parseDouble(value));
        }
    }

    @Override
    public void buildTimeline(String key, String value) {
        if (key.equals("timestamp")) {
            OperationTimeline ot = new OperationTimeline();
            ot.setTimestamp(value);
            mission.addTimeline(ot);
        } else {
            List<OperationTimeline> list = mission.getOperationTimeline();
            if (list.isEmpty()) return;
            OperationTimeline last = list.get(list.size() - 1);
            if (key.equals("type")) last.setType(value);
            else if (key.equals("description")) last.setDescription(value);
        }
    }

    @Override
    public void buildExtra(String key, String value) {
        switch (key) {
            case "tags" -> splitList(value).forEach(mission::addTag);
            case "support" -> splitList(value).forEach(mission::addSupport);
            case "artifacts" -> splitList(value).forEach(mission::addArtifact);
            case "recommendations" -> splitList(value).forEach(mission::addRecommendation);
            case "notes" -> mission.setNotes(value);
        }
    }

    private Long parseLong(String v) {
        try { return (long) Double.parseDouble(v); } 
        catch (Exception e) { return 0L; }
    }

    private double parseDouble(String v) {
        try { return Double.parseDouble(v); } 
        catch (Exception e) { return 0.0; }
    }

    private List<String> splitList(String v) {
        return java.util.Arrays.stream(v.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}

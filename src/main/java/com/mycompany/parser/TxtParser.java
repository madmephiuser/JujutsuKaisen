
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.*;
import enums.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TxtParser extends AbstractMissionParser {
    
    @Override
    protected boolean doCheck(String filePath) {
        String line = getFirstLine(filePath);
        return line != null && line.startsWith("[");
    }
    
    @Override
    public Mission doParse(String filePath) {
        Mission.Builder builder = Mission.builder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentSection = "";

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.toUpperCase();
                    continue;
                }
                String[] parts = line.split("=", 2);
                if (parts.length < 2) continue;
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (currentSection) {
                    case "[MISSION]" -> parseMission(builder, key, value);
                    case "[CURSE]" -> parseCurse(builder, key, value);
                    case "[SORCERER]" -> parseSorcerer(builder, key, value);
                    case "[TECHNIQUE]" -> parseTechnique(builder, key, value);
                    case "[ECONOMIC]" -> parseEconomic(builder, key, value);
                    case "[CIVILIAN]" -> parseCivilian(builder, key, value);
                    case "[ENEMY]" -> parseEnemy(builder, key, value);
                    case "[ENVIRONMENT]" -> parseEnvironment(builder, key, value);
                    case "[TIMELINE]" -> parseTimeline(builder, key, value);
                    case "[EXTRA]" -> parseExtra(builder, key, value);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка TXT парсера: " + e.getMessage());
        }
        return builder.build();
    }
    
    private void parseMission(Mission.Builder b, String key, String value) {
        switch (key) {
            case "missionId" -> b.missionId(value);
            case "date" -> b.date(value);
            case "location" -> b.location(value);
            case "outcome" -> b.outcome(MissionOutcome.fromString(value));
            case "damageCost" -> b.damageCost(parseLong(value));
        }
    }

    private void parseCurse(Mission.Builder b, String key, String value) {
        Curse c = b.getCurse();
        if (key.equals("name")) c.setName(value);
        else if (key.equals("threatLevel")) c.setThreatLevel(ThreatLevel.fromString(value));
    }

    private void parseSorcerer(Mission.Builder b, String key, String value) {
        if (key.equals("name")) {
            b.addSorcerer(new Sorcerer(value));
        } else if (key.equals("rank")) {
            Sorcerer last = b.getLastSorcerer();
            if (last != null) last.setRank(SorcererRank.fromString(value));
        }
    }

    private void parseTechnique(Mission.Builder b, String key, String value) {
        if (key.equals("name")) {
            Technique t = new Technique();
            t.setName(value);
            b.addTechnique(t);
        } else {
            Technique last = b.getLastTechnique();
            if (last != null) {
                switch (key) {
                    case "type" -> last.setType(TechniqueType.fromString(value));
                    case "owner" -> last.setOwner(value);
                    case "damage" -> last.setDamage(parseLong(value));
                }
            }
        }
    }

    private void parseEconomic(Mission.Builder b, String key, String value) {
        EconomicAssessment ec = b.getEconomicAssessment();
        switch (key) {
            case "totalDamageCost" -> ec.setTotalDamageCost(parseDouble(value));
            case "infrastructureDamage" -> ec.setInfrastructureDamage(parseDouble(value));
            case "commercialDamage" -> ec.setCommercialDamage(parseDouble(value));
            case "transportDamage" -> ec.setTransportDamage(parseDouble(value));
            case "recoveryEstimateDays" -> ec.setRecoveryEstimateDays(Integer.parseInt(value));
            case "insuranceCovered" -> ec.setInsuranceCovered(Boolean.parseBoolean(value));
        }
    }

    private void parseCivilian(Mission.Builder b, String key, String value) {
        CivilianImpact ci = b.getCivilianImpact();
        switch (key) {
            case "evacuated" -> ci.setEvacuated(Integer.valueOf(value));
            case "injured" -> ci.setInjured(Integer.parseInt(value));
            case "missing" -> ci.setMissing(Integer.parseInt(value));
            case "publicExposureRisk" -> ci.setPublicExposureRisk(value);
        }
    }

    private void parseEnemy(Mission.Builder b, String key, String value) {
        EnemyActivity en = b.getEnemyActivity();
        switch (key) {
            case "behaviorType" -> en.setBehaviorType(value);
            case "mobility" -> en.setMobility(Mobility.fromString(value));
            case "escalationRisk" -> en.setEscalationRisk(RiskLevel.fromString(value));
            case "targetPriority" -> en.setTargetPriority(splitList(value));
            case "attackPatterns" -> en.setAttackPatterns(splitList(value));
        }
    }

    private void parseEnvironment(Mission.Builder b, String key, String value) {
        EnvironmentConditions env = b.getEnvironmentConditions();
        switch (key) {
            case "weather" -> env.setWeather(value);
            case "timeOfDay" -> env.setTimeOfDay(value);
            case "visibility" -> env.setVisibility(Visibility.fromString(value));
            case "cursedEnergyDensity" -> env.setCursedEnergyDensity(parseDouble(value));
        }
    }

    private void parseTimeline(Mission.Builder b, String key, String value) {
        if (key.equals("timestamp")) {
            OperationTimeline ot = new OperationTimeline();
            ot.setTimestamp(value);
            b.addTimeline(ot);
        } else {
            OperationTimeline last = b.getLastTimeline();
            if (last != null) {
                if (key.equals("type")) last.setType(value);
                else if (key.equals("description")) last.setDescription(value);
            }
        }
    }

    private void parseExtra(Mission.Builder b, String key, String value) {
        switch (key) {
            case "tags" -> splitList(value).forEach(b::addTag);
            case "support" -> splitList(value).forEach(b::addSupportUnit);
            case "recommendations" -> splitList(value).forEach(b::addRecommendation);
            case "artifacts" -> splitList(value).forEach(b::addArtifact);
            case "zones" -> splitList(value).forEach(b::addEvacuationZone);
            case "effects" -> splitList(value).forEach(b::addStatusEffect);
            case "notes" -> b.notes(value);
        }
    }
    private Long parseLong(String val) {
        if (val == null || val.isBlank()) return null;
        return (long) Double.parseDouble(val);
    }

    private Double parseDouble(String val) {
        return Double.valueOf(val);
    }

    private ArrayList<String> splitList(String val) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : val.split(",")) {
            list.add(s.trim());
        }
        return list;
    }
}
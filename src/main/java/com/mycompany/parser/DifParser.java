
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.*;
import enums.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DifParser extends AbstractMissionParser {
    
    @Override
    protected boolean doCheck(String filePath) {
        String firstLine = getFirstLine(filePath);
        return firstLine != null && firstLine.contains("|");
    }
    
    @Override
    public Mission doParse(String filePath) {
        Mission.Builder builder = Mission.builder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split("\\|");
                for (int i = 0; i < tokens.length; i++) tokens[i] = tokens[i].trim();
                
                String eventType = tokens[0].toUpperCase();

                switch (eventType) {
                    case "MISSION_CREATED" -> parseMissionCreated(builder, tokens);
                    case "CURSE_DETECTED" -> parseCurseDetected(builder, tokens);
                    case "SORCERER_ASSIGNED" -> parseSorcererAssigned(builder, tokens);
                    case "TECHNIQUE_USED" -> parseTechniqueUsed(builder, tokens);
                    case "ECONOMIC_ASSESSMENT" -> parseEconomic(builder, tokens);
                    case "CIVILIAN_IMPACT" -> parseCivilianImpact(builder, tokens);
                    case "ENEMY_ACTION" -> parseEnemyAction(builder, tokens);
                    case "ENVIRONMENT_REPORT" -> parseEnvironment(builder, tokens);
                    case "TIMELINE_EVENT" -> parseTimelineEvent(builder, tokens);
                    case "MISSION_RESULT" -> parseMissionResult(builder, tokens);
                    case "EXTRA_DATA" -> parseExtra(builder, tokens);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка DIF парсера: " + e.getMessage());
        }
        return builder.build();
    }
    private void parseMissionCreated(Mission.Builder b, String[] tokens) {
        if (tokens.length > 1) b.missionId(tokens[1]);
        if (tokens.length > 2) b.date(tokens[2]);
        if (tokens.length > 3) b.location(tokens[3]);
    }

    private void parseCurseDetected(Mission.Builder b, String[] tokens) {
        Curse curse = b.getCurse();
        if (tokens.length > 1) curse.setName(tokens[1]);
        if (tokens.length > 2) curse.setThreatLevel(ThreatLevel.fromString(tokens[2]));
    }

    private void parseSorcererAssigned(Mission.Builder b, String[] tokens) {
        if (tokens.length > 2) {
            Sorcerer s = new Sorcerer(tokens[1]);
            s.setRank(SorcererRank.fromString(tokens[2]));
            b.addSorcerer(s);
        }
    }

    private void parseTechniqueUsed(Mission.Builder b, String[] tokens) {
        if (tokens.length > 4) {
            Technique t = new Technique();
            t.setName(tokens[1]);
            t.setType(TechniqueType.fromString(tokens[2]));
            t.setOwner(tokens[3]);
            t.setDamage(parseLong(tokens[4]));
            b.addTechnique(t);
        }
    }

    private void parseEconomic(Mission.Builder b, String[] tokens) {
        EconomicAssessment ec = b.getEconomicAssessment();
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=", 2);
            if (kv.length < 2) continue;
            String val = kv[1].trim();
            switch (kv[0].trim()) {
                case "total" -> ec.setTotalDamageCost(parseDouble(val));
                case "infrastructure" -> ec.setInfrastructureDamage(parseDouble(val));
                case "recovery" -> ec.setRecoveryEstimateDays(Integer.parseInt(val));
                case "insurance" -> ec.setInsuranceCovered(Boolean.parseBoolean(val));
            }
        }
    }

    private void parseCivilianImpact(Mission.Builder b, String[] tokens) {
        CivilianImpact ci = b.getCivilianImpact();
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=", 2);
            if (kv.length < 2) continue;
            String val = kv[1].trim();
            switch (kv[0].trim()) {
                case "evacuated" -> ci.setEvacuated(Integer.parseInt(val));
                case "injured" -> ci.setInjured(Integer.parseInt(val));
                case "missing" -> ci.setMissing(Integer.parseInt(val));
            }
        }
    }

    private void parseEnemyAction(Mission.Builder b, String[] tokens) {
        EnemyActivity ea = b.getEnemyActivity();
        if (tokens.length > 1) ea.setBehaviorType(tokens[1]);
        if (tokens.length > 2) ea.getAttackPatterns().add(tokens[2]);
        if (tokens.length > 3) ea.getTargetPriority().add(tokens[3]);
        if (tokens.length > 4) ea.setMobility(Mobility.fromString(tokens[4]));
        if (tokens.length > 5) ea.setEscalationRisk(RiskLevel.fromString(tokens[5]));
    }

    private void parseEnvironment(Mission.Builder b, String[] tokens) {
        EnvironmentConditions env = b.getEnvironmentConditions();
        if (tokens.length > 1) env.setWeather(tokens[1]);
        if (tokens.length > 2) env.setTimeOfDay(tokens[2]);
        if (tokens.length > 3) env.setVisibility(Visibility.fromString(tokens[3]));
        if (tokens.length > 4) env.setCursedEnergyDensity(parseDouble(tokens[4]));
    }

    private void parseTimelineEvent(Mission.Builder b, String[] tokens) {
        if (tokens.length > 3) {
            OperationTimeline event = new OperationTimeline();
            event.setTimestamp(tokens[1]);
            event.setType(tokens[2]);
            event.setDescription(tokens[3]);
            b.addTimeline(event);
        }
    }

    private void parseMissionResult(Mission.Builder b, String[] tokens) {
        if (tokens.length > 1) b.outcome(MissionOutcome.fromString(tokens[1]));
        if (tokens.length > 2) {
            String costRaw = tokens[2].contains("=") ? tokens[2].split("=")[1] : tokens[2];
            b.damageCost(parseLong(costRaw));
        }
    }

    private void parseExtra(Mission.Builder b, String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=", 2);
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String val = kv[1].trim();
            switch (key) {
                case "tags" -> splitList(val).forEach(b::addTag);
                case "support" -> splitList(val).forEach(b::addSupportUnit);
                case "artifacts" -> splitList(val).forEach(b::addArtifact);
                case "notes" -> b.notes(val);
            }
        }
    }
    private Long parseLong(String v) {
        if (v == null || v.isBlank()) return null;
        try {
            return (long) Double.parseDouble(v); 
        } catch (Exception e) {
            return 0L; 
        }
    }

    private double parseDouble(String v) {
        try { 
            return Double.parseDouble(v); 
        } catch (Exception e) { 
            return 0.0; 
        }
    }
    
    private List<String> splitList(String v) {
        return Arrays.stream(v.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
}
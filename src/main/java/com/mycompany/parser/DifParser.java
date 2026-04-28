package com.mycompany.parser;

import com.mycompany.jujutsukaisen.*;
import enums.*;
import java.io.*;
import java.util.ArrayList;
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
        Mission mission = new Mission();

        mission.setSorcerers(new ArrayList<>());
        mission.setTechniques(new ArrayList<>());
        mission.setOperationTimeline(new ArrayList<>());
        mission.setOperationTags(new ArrayList<>());
        mission.setSupportUnits(new ArrayList<>());
        mission.setRecommendations(new ArrayList<>());
        mission.setArtifactsRecovered(new ArrayList<>());
        mission.setEvacuationZones(new ArrayList<>());
        mission.setStatusEffects(new ArrayList<>());

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] tokens = line.split("\\|");
                for (int i = 0; i < tokens.length; i++) tokens[i] = tokens[i].trim();
                
                if (tokens.length == 0) continue;
                String eventType = tokens[0].toUpperCase();

                switch (eventType) {
                    case "MISSION_CREATED" -> parseMissionCreated(mission, tokens);
                    case "CURSE_DETECTED" -> parseCurseDetected(mission, tokens);
                    case "SORCERER_ASSIGNED" -> parseSorcererAssigned(mission, tokens);
                    case "TECHNIQUE_USED" -> parseTechniqueUsed(mission, tokens);
                    case "ECONOMIC_ASSESSMENT" -> parseEconomic(mission, tokens);
                    case "CIVILIAN_IMPACT" -> parseCivilianImpact(mission, tokens);
                    case "ENEMY_ACTION" -> parseEnemyAction(mission, tokens);
                    case "ENVIRONMENT_REPORT" -> parseEnvironment(mission, tokens);
                    case "TIMELINE_EVENT" -> parseTimelineEvent(mission, tokens);
                    case "MISSION_RESULT" -> parseMissionResult(mission, tokens);
                    case "EXTRA_DATA" -> parseExtra(mission, tokens);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка DIF парсера: " + e.getMessage());
            e.printStackTrace();
        }
        return mission;
    }

    private void parseMissionCreated(Mission m, String[] tokens) {
        if (tokens.length > 1) m.setMissionId(tokens[1]);
        if (tokens.length > 2) m.setDate(tokens[2]);
        if (tokens.length > 3) m.setLocation(tokens[3]);
    }

    private void parseCurseDetected(Mission m, String[] tokens) {
        if (m.getCurse() == null) m.setCurse(new Curse());
        if (tokens.length > 1) m.getCurse().setName(tokens[1]);
        if (tokens.length > 2) m.getCurse().setThreatLevel(ThreatLevel.fromString(tokens[2]));
    }

    private void parseSorcererAssigned(Mission m, String[] tokens) {
        if (tokens.length > 2) {
            Sorcerer s = new Sorcerer(tokens[1]);
            s.setRank(SorcererRank.fromString(tokens[2]));
            s.setMission(m);
            m.getSorcerers().add(s);
        }
    }

    private void parseTechniqueUsed(Mission m, String[] tokens) {
        if (tokens.length > 4) {
            Technique t = new Technique();
            t.setName(tokens[1]);
            t.setType(TechniqueType.fromString(tokens[2]));
            t.setOwner(tokens[3]);
            t.setDamage(parseLong(tokens[4]));
            t.setMission(m);
            m.getTechniques().add(t);
        }
    }

    private void parseEconomic(Mission m, String[] tokens) {
        if (m.getEconomicAssessment() == null) m.setEconomicAssessment(new EconomicAssessment());
        EconomicAssessment ec = m.getEconomicAssessment();
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=", 2);
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String val = kv[1].trim();
            switch (key) {
                case "total" -> ec.setTotalDamageCost(parseDouble(val));
                case "infrastructure" -> ec.setInfrastructureDamage(parseDouble(val));
                case "commercial" -> ec.setCommercialDamage(parseDouble(val));
                case "transport" -> ec.setTransportDamage(parseDouble(val));
                case "recovery" -> ec.setRecoveryEstimateDays(Integer.parseInt(val));
                case "insurance" -> ec.setInsuranceCovered(Boolean.parseBoolean(val));
            }
        }
    }

    private void parseCivilianImpact(Mission m, String[] tokens) {
        if (m.getCivilianImpact() == null) m.setCivilianImpact(new CivilianImpact());
        CivilianImpact ci = m.getCivilianImpact();
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=", 2);
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String val = kv[1].trim();
            if (val.isEmpty()) continue;

            switch (key) {
                case "evacuated" -> ci.setEvacuated(Integer.parseInt(val));
                case "injured" -> ci.setInjured(Integer.parseInt(val));
                case "missing" -> ci.setMissing(Integer.parseInt(val));
                case "risk" -> ci.setPublicExposureRisk(val);
            }
        }
    }

    private void parseEnemyAction(Mission m, String[] tokens) {
        if (m.getEnemyActivity() == null) m.setEnemyActivity(new EnemyActivity());
        EnemyActivity ea = m.getEnemyActivity();
        if (tokens.length > 1) ea.setBehaviorType(tokens[1]);
        if (tokens.length > 2) ea.getAttackPatterns().addAll(splitList(tokens[2]));
        if (tokens.length > 3) ea.getTargetPriority().addAll(splitList(tokens[3]));
        if (tokens.length > 4) ea.setMobility(Mobility.fromString(tokens[4]));
        if (tokens.length > 5) ea.setEscalationRisk(RiskLevel.fromString(tokens[5]));
    }

    private void parseEnvironment(Mission m, String[] tokens) {
        if (m.getEnvironmentConditions() == null) m.setEnvironmentConditions(new EnvironmentConditions());
        EnvironmentConditions env = m.getEnvironmentConditions();
        if (tokens.length > 1) env.setWeather(tokens[1]);
        if (tokens.length > 2) env.setTimeOfDay(tokens[2]);
        if (tokens.length > 3) env.setVisibility(Visibility.fromString(tokens[3]));
        if (tokens.length > 4) env.setCursedEnergyDensity(parseDouble(tokens[4]));
    }

    private void parseTimelineEvent(Mission m, String[] tokens) {
        if (tokens.length > 3) {
            OperationTimeline event = new OperationTimeline();
            event.setTimestamp(tokens[1]);
            event.setType(tokens[2]);
            event.setDescription(tokens[3]);
            m.getOperationTimeline().add(event);
        }
    }

    private void parseMissionResult(Mission m, String[] tokens) {
        if (tokens.length > 1) m.setOutcome(MissionOutcome.fromString(tokens[1]));
        if (tokens.length > 2) {
            String val = tokens[2].contains("=") ? tokens[2].split("=")[1] : tokens[2];
            m.setDamageCost(parseLong(val));
        }
    }

    private void parseExtra(Mission m, String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=", 2);
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String val = kv[1].trim();
            switch (key) {
                case "tags" -> m.getOperationTags().addAll(splitList(val));
                case "support" -> m.getSupportUnits().addAll(splitList(val));
                case "recommendations" -> m.getRecommendations().addAll(splitList(val));
                case "artifacts" -> m.getArtifactsRecovered().addAll(splitList(val));
                case "zones" -> m.getEvacuationZones().addAll(splitList(val));
                case "effects" -> m.getStatusEffects().addAll(splitList(val));
                case "notes" -> m.setNotes(val);
            }
        }
    }
    
    private Long parseLong(String val) {
        try { return (long) Double.parseDouble(val); } catch (Exception e) { return 0L; }
    }

    private Double parseDouble(String val) {
        try { return Double.valueOf(val); } catch (Exception e) { return 0.0; }
    }

    private List<String> splitList(String val) {
        if (val == null || val.isEmpty()) return new ArrayList<>();
        return Arrays.stream(val.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
}
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.*;
import enums.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtParser extends AbstractMissionParser {

    @Override
    protected boolean doCheck(String filePath) {
        String line = getFirstLine(filePath);
        return line != null && line.startsWith("[");
    }

    @Override
    public Mission doParse(String filePath) {
        Mission mission = new Mission();
        if (mission.getSorcerers() == null) mission.setSorcerers(new ArrayList<>());
        if (mission.getTechniques() == null) mission.setTechniques(new ArrayList<>());

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
                    case "[MISSION]" -> parseMission(mission, key, value);
                    case "[CURSE]" -> parseCurse(mission, key, value);
                    case "[SORCERER]" -> parseSorcerer(mission, key, value);
                    case "[TECHNIQUE]" -> parseTechnique(mission, key, value);
                    case "[ECONOMIC]" -> parseEconomic(mission, key, value);
                    case "[CIVILIAN]" -> parseCivilian(mission, key, value);
                    case "[ENEMY]" -> parseEnemy(mission, key, value);
                    case "[ENVIRONMENT]" -> parseEnvironment(mission, key, value);
                    case "[TIMELINE]" -> parseTimeline(mission, key, value);
                    case "[EXTRA]" -> parseExtra(mission, key, value);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка TXT парсера: " + e.getMessage());
            e.printStackTrace();
        }
        return mission;
    }

    private void parseMission(Mission m, String key, String value) {
        switch (key) {
            case "missionId" -> m.setMissionId(value);
            case "date" -> m.setDate(value);
            case "location" -> m.setLocation(value);
            case "outcome" -> m.setOutcome(MissionOutcome.fromString(value));
            case "damageCost" -> m.setDamageCost(parseLong(value));
        }
    }

    private void parseCurse(Mission m, String key, String value) {
        if (m.getCurse() == null) m.setCurse(new Curse());
        if (key.equals("name")) m.getCurse().setName(value);
        else if (key.equals("threatLevel")) m.getCurse().setThreatLevel(ThreatLevel.fromString(value));
    }

    private void parseSorcerer(Mission m, String key, String value) {
        if (key.equals("name")) {
            Sorcerer s = new Sorcerer(value);
            s.setMission(m);
            m.getSorcerers().add(s);
        } else if (key.equals("rank")) {
            if (!m.getSorcerers().isEmpty()) {
                m.getSorcerers().get(m.getSorcerers().size() - 1).setRank(SorcererRank.fromString(value));
            }
        }
    }

    private void parseTechnique(Mission m, String key, String value) {
        if (key.equals("name")) {
            Technique t = new Technique();
            t.setName(value);
            t.setMission(m);
            m.getTechniques().add(t);
        } else if (!m.getTechniques().isEmpty()) {
            Technique last = m.getTechniques().get(m.getTechniques().size() - 1);
            switch (key) {
                case "type" -> last.setType(TechniqueType.fromString(value));
                case "owner" -> last.setOwner(value);
                case "damage" -> last.setDamage(parseLong(value));
            }
        }
    }

    private void parseEconomic(Mission m, String key, String value) {
        if (m.getEconomicAssessment() == null) m.setEconomicAssessment(new EconomicAssessment());
        EconomicAssessment ec = m.getEconomicAssessment();
        switch (key) {
            case "totalDamageCost" -> ec.setTotalDamageCost(parseDouble(value));
            case "infrastructureDamage" -> ec.setInfrastructureDamage(parseDouble(value));
            case "commercialDamage" -> ec.setCommercialDamage(parseDouble(value));
            case "transportDamage" -> ec.setTransportDamage(parseDouble(value));
            case "recoveryEstimateDays" -> ec.setRecoveryEstimateDays(Integer.parseInt(value));
            case "insuranceCovered" -> ec.setInsuranceCovered(Boolean.parseBoolean(value));
        }
    }

    private void parseCivilian(Mission m, String key, String value) {
        if (value == null || value.isEmpty()) return;
        if (m.getCivilianImpact() == null) m.setCivilianImpact(new CivilianImpact());
        CivilianImpact ci = m.getCivilianImpact();
        switch (key) {
            case "evacuated" -> ci.setEvacuated(Integer.valueOf(value));
            case "injured" -> ci.setInjured(Integer.parseInt(value));
            case "missing" -> ci.setMissing(Integer.parseInt(value));
            case "publicExposureRisk" -> ci.setPublicExposureRisk(value);
        }
    }

    private void parseEnemy(Mission m, String key, String value) {
        if (value == null || value.isEmpty()) return;
        if (m.getEnemyActivity() == null) m.setEnemyActivity(new EnemyActivity());
        EnemyActivity en = m.getEnemyActivity();
        switch (key) {
            case "behaviorType" -> en.setBehaviorType(value);
            case "mobility" -> en.setMobility(Mobility.fromString(value));
            case "escalationRisk" -> en.setEscalationRisk(RiskLevel.fromString(value));
            case "targetPriority" -> en.getTargetPriority().addAll(splitList(value));
            case "attackPatterns" -> en.getAttackPatterns().addAll(splitList(value));
        }
    }

    private void parseEnvironment(Mission m, String key, String value) {
        if (m.getEnvironmentConditions() == null) m.setEnvironmentConditions(new EnvironmentConditions());
        EnvironmentConditions env = m.getEnvironmentConditions();
        switch (key) {
            case "weather" -> env.setWeather(value);
            case "timeOfDay" -> env.setTimeOfDay(value);
            case "visibility" -> env.setVisibility(Visibility.fromString(value));
            case "cursedEnergyDensity" -> env.setCursedEnergyDensity(parseDouble(value));
        }
    }

    private void parseTimeline(Mission m, String key, String value) {
        if (key.equals("timestamp")) {
            OperationTimeline ot = new OperationTimeline();
            ot.setTimestamp(value);
            m.getOperationTimeline().add(ot);
        } else if (!m.getOperationTimeline().isEmpty()) {
            OperationTimeline last = m.getOperationTimeline().get(m.getOperationTimeline().size() - 1);
            if (key.equals("type")) last.setType(value);
            else if (key.equals("description")) last.setDescription(value);
        }
    }

    private void parseExtra(Mission m, String key, String value) {
        switch (key) {
            case "tags" -> m.getOperationTags().addAll(splitList(value));
            case "support" -> m.getSupportUnits().addAll(splitList(value));
            case "recommendations" -> m.getRecommendations().addAll(splitList(value));
            case "artifacts" -> m.getArtifactsRecovered().addAll(splitList(value));
            case "zones" -> m.getEvacuationZones().addAll(splitList(value));
            case "effects" -> m.getStatusEffects().addAll(splitList(value));
            case "notes" -> m.setNotes(value);
        }
    }

    private Long parseLong(String val) {
        try {
            return (long) Double.parseDouble(val); 
        } catch (Exception e) {
            return 0L; 
        }
    }

    private Double parseDouble(String val) {
        try {
            return Double.valueOf(val); 
        } catch (Exception e) {
            return 0.0; 
        }
    }

    private List<String> splitList(String val) {
        if (val == null || val.isEmpty()) return new ArrayList<>();
        return Arrays.stream(val.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
}

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
        return line.startsWith("[");
    }
    
    @Override
    public Mission doParse(String filePath) {
        Mission m = new Mission();
        m.setSorcerers(new ArrayList<>());
        m.setTechniques(new ArrayList<>());
        m.setOperationTimeline(new ArrayList<>());
        m.setOperationTags(new ArrayList<>());
        m.setSupportUnits(new ArrayList<>());
        m.setRecommendations(new ArrayList<>());
        m.setArtifactsRecovered(new ArrayList<>());
        m.setEvacuationZones(new ArrayList<>());
        m.setStatusEffects(new ArrayList<>());

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
                    case "[MISSION]" -> {
                    switch (key) {
                        case "missionId" -> m.setMissionId(value);
                        case "date" -> m.setDate(value);
                        case "location" -> m.setLocation(value);
                        case "outcome" -> m.setOutcome(MissionOutcome.fromString(value));
                        case "damageCost" -> m.setDamageCost(parseLong(value));
                        default -> {
                        }
                    }
                    }

                    case "[CURSE]" -> {
                        if (m.getCurse() == null) m.setCurse(new Curse());
                        if (key.equals("name")) m.getCurse().setName(value);
                        else if (key.equals("threatLevel")) m.getCurse().setThreatLevel(ThreatLevel.fromString(value));
                    }

                    case "[SORCERER]" -> {
                        if (key.equals("name")) {
                            Sorcerer s = new Sorcerer();
                            s.setName(value);
                            m.getSorcerers().add(s);
                        } else if (key.equals("rank") && !m.getSorcerers().isEmpty()) {
                            m.getSorcerers().get(m.getSorcerers().size() - 1).setRank(SorcererRank.fromString(value));
                        }
                    }

                    case "[TECHNIQUE]" -> {
                        if (key.equals("name")) {
                            Technique t = new Technique();
                            t.setName(value);
                            m.getTechniques().add(t);
                        } else if (!m.getTechniques().isEmpty()) {
                            Technique last = m.getTechniques().get(m.getTechniques().size() - 1);
                            switch (key) {
                                case "type" -> last.setType(TechniqueType.fromString(value));
                                case "owner" -> last.setOwner(value);
                                case "damage" -> last.setDamage(parseLong(value));
                                default -> {
                                }
                            }
                        }
                    }

                    case "[ECONOMIC]" -> {
                        if (m.getEconomicAssessment() == null) m.setEconomicAssessment(new EconomicAssessment());
                        EconomicAssessment ec = m.getEconomicAssessment();
                    switch (key) {
                        case "totalDamageCost" -> ec.setTotalDamageCost(parseDouble(value));
                        case "infrastructureDamage" -> ec.setInfrastructureDamage(parseDouble(value));
                        case "commercialDamage" -> ec.setCommercialDamage(parseDouble(value));
                        case "transportDamage" -> ec.setTransportDamage(parseDouble(value));
                        case "recoveryEstimateDays" -> ec.setRecoveryEstimateDays(Integer.parseInt(value));
                        case "insuranceCovered" -> ec.setInsuranceCovered(Boolean.parseBoolean(value));
                        default -> {
                        }
                    }
                    }

                    case "[CIVILIAN]" -> {
                        if (m.getCivilianImpact() == null) m.setCivilianImpact(new CivilianImpact());
                        CivilianImpact ci = m.getCivilianImpact();
                    switch (key) {
                        case "evacuated" -> ci.setEvacuated(Integer.valueOf(value));
                        case "injured" -> ci.setInjured(Integer.parseInt(value));
                        case "missing" -> ci.setMissing(Integer.parseInt(value));
                        case "publicExposureRisk" -> ci.setPublicExposureRisk(value);
                        default -> {
                        }
                    }
                    }

                    case "[ENEMY]" -> {
                        if (m.getEnemyActivity() == null) m.setEnemyActivity(new EnemyActivity());
                        EnemyActivity en = m.getEnemyActivity();
                        if (key.equals("behaviorType")) en.setBehaviorType(value);
                        else if (key.equals("mobility")) en.setMobility(Mobility.fromString(value));
                        else if (key.equals("escalationRisk")) en.setEscalationRisk(RiskLevel.fromString(value));
                        else if (key.equals("targetPriority")) en.setTargetPriority(splitList(value));
                        else if (key.equals("attackPatterns")) en.setAttackPatterns(splitList(value));
                    }

                    case "[ENVIRONMENT]" -> {
                        if (m.getEnvironmentConditions() == null) m.setEnvironmentConditions(new EnvironmentConditions());
                        EnvironmentConditions env = m.getEnvironmentConditions();
                    switch (key) {
                        case "weather" -> env.setWeather(value);
                        case "timeOfDay" -> env.setTimeOfDay(value);
                        case "visibility" -> env.setVisibility(Visibility.fromString(value));
                        case "cursedEnergyDensity" -> env.setCursedEnergyDensity(parseDouble(value));
                        default -> {
                        }
                    }
                    }

                    case "[TIMELINE]" -> {
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

                    case "[EXTRA]" -> {
                        switch (key) {
                            case "tags" -> m.setOperationTags(splitList(value));
                            case "support" -> m.setSupportUnits(splitList(value));
                            case "recommendations" -> m.setRecommendations(splitList(value));
                            case "artifacts" -> m.setArtifactsRecovered(splitList(value));
                            case "zones" -> m.setEvacuationZones(splitList(value));
                            case "effects" -> m.setStatusEffects(splitList(value));
                            case "notes" -> m.setNotes(value);
                            default -> {
                            }
                        }
                    }


                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка TXT парсера: " + e.getMessage());
        }
        return m;
    }

    private Long parseLong(String val) {
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
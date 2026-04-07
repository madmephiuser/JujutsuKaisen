
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.*;
import enums.*;
import java.io.*;
import java.util.ArrayList;

public class DifParser implements MissionParser {

    @Override
    public Mission parse(String filePath) {
        Mission mission = new Mission();
        mission.setSorcerers(new ArrayList<>());
        mission.setTechniques(new ArrayList<>());
        mission.setOperationTimeline(new ArrayList<>());

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split("\\|");
                for (int i = 0; i < tokens.length; i++) tokens[i] = tokens[i].trim();
                
                String eventType = tokens[0].toUpperCase();

                switch (eventType) {
                    case "MISSION_CREATED" -> {
                        mission.setMissionId(tokens[1]);
                        mission.setDate(tokens[2]);
                        mission.setLocation(tokens[3]);
                    }
                    case "CURSE_DETECTED" -> {
                        Curse curse = new Curse();
                        curse.setName(tokens[1]);
                        curse.setThreatLevel(ThreatLevel.fromString(tokens[2]));
                        mission.setCurse(curse);
                    }
                    case "SORCERER_ASSIGNED" -> {
                        Sorcerer s = new Sorcerer();
                        s.setName(tokens[1]);
                        s.setRank(SorcererRank.fromString(tokens[2]));
                        mission.getSorcerers().add(s);
                    }
                    case "TECHNIQUE_USED" -> { 
                        Technique t = new Technique();
                        t.setName(tokens[1]);
                        t.setType(TechniqueType.fromString(tokens[2]));
                        t.setOwner(tokens[3]);
                        t.setDamage((long) Double.parseDouble(tokens[4]));
                        mission.getTechniques().add(t);
                    }
                    case "TIMELINE_EVENT" -> {
                        OperationTimeline event = new OperationTimeline();
                        event.setTimestamp(tokens[1]);
                        event.setType(tokens[2]);
                        event.setDescription(tokens[3]);
                        mission.getOperationTimeline().add(event);
                    }
                    case "ENEMY_ACTION" -> {
                        if (mission.getEnemyActivity() == null) {
                            EnemyActivity ea = new EnemyActivity();
                            ea.setAttackPatterns(new ArrayList<>());
                            ea.setTargetPriority(new ArrayList<>());
                            mission.setEnemyActivity(ea);
                        }
                        EnemyActivity activity = mission.getEnemyActivity();
                        if (tokens.length > 1 && !tokens[1].trim().isEmpty()) {
                            String behavior = tokens[1].trim();
                            String current = activity.getBehaviorType();
                            if (current == null || current.isEmpty()) {
                                activity.setBehaviorType(behavior);
                            } else if (!current.contains(behavior)) {
                                activity.setBehaviorType(current + ", " + behavior);
                            }
                        }
                        if (tokens.length > 2 && !tokens[2].trim().isEmpty()) {
                            String pattern = tokens[2].trim();
                            if (!activity.getAttackPatterns().contains(pattern)) {
                                activity.getAttackPatterns().add(pattern);
                            }
                        }
                        if (tokens.length > 3 && !tokens[3].trim().isEmpty()) {
                            String target = tokens[3].trim();
                            if (!activity.getTargetPriority().contains(target)) {
                                activity.getTargetPriority().add(target);
                            }
                        }
                        if (tokens.length > 4 && !tokens[4].trim().isEmpty()) {
                            activity.setMobility(Mobility.fromString(tokens[4].trim()));
                        }
                        if (tokens.length > 5 && !tokens[5].trim().isEmpty()) {
                            activity.setEscalationRisk(RiskLevel.fromString(tokens[5].trim()));
                        }
                    }
                    case "CIVILIAN_IMPACT" -> {
                        if (mission.getCivilianImpact() == null) mission.setCivilianImpact(new CivilianImpact());
                        parseKeyValueToCivilian(mission.getCivilianImpact(), tokens);
                    }
                    case "MISSION_RESULT" -> {
                        mission.setOutcome(MissionOutcome.fromString(tokens[1]));
                        if (tokens.length > 2) {
                            String cost = tokens[2].contains("=") ? tokens[2].split("=")[1] : tokens[2];
                            mission.setDamageCost((long) Double.parseDouble(cost));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mission;
    }

    private void parseKeyValueToCivilian(CivilianImpact ci, String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            String[] kv = tokens[i].split("=");
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String val = kv[1].trim();
            if (key.equals("evacuated")) ci.setEvacuated(Integer.parseInt(val));
            else if (key.equals("injured")) ci.setInjured(Integer.parseInt(val));
            else if (key.equals("missing")) ci.setMissing(Integer.parseInt(val));
        }
    }
}
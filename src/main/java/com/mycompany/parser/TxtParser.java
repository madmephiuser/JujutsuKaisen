/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.Curse;
import com.mycompany.jujutsukaisen.Mission;
import com.mycompany.jujutsukaisen.Sorcerer;
import com.mycompany.jujutsukaisen.Technique;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class TxtParser implements MissionParser {
    @Override
    public Mission parse(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lines.isEmpty()) return null;

            String firstLine = lines.get(0).trim();
            if (firstLine.startsWith("{") || firstLine.startsWith("<")) {
                System.out.println("Ошибка неверный формат файла");
                return null;
            }

            Mission mission = new Mission();
            mission.setCurse(new Curse());
            mission.setSorcerers(new ArrayList<>());
            mission.setTechniques(new ArrayList<>());

            for (String line : lines) {
                if (!line.contains(":")) continue;
                String[] parts = line.split(":", 2);
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "missionId" -> mission.setMissionId(value);
                    case "location" -> mission.setLocation(value);
                    case "outcome" -> mission.setOutcome(value);
                    case "damageCost" -> mission.setDamageCost(Long.parseLong(value));
                    case "note", "comment" -> mission.setComment(value);
                }

                if (key.startsWith("curse.")) {
                    if (key.endsWith(".name")) mission.getCurse().setName(value);
                    if (key.endsWith(".threatLevel")) mission.getCurse().setThreatLevel(value);
                }

                if (key.startsWith("sorcerer[")) {
                    int index = extractIndex(key);
                    while (mission.getSorcerers().size() <= index) mission.getSorcerers().add(new Sorcerer());
                    if (key.endsWith(".name")) mission.getSorcerers().get(index).setName(value);
                    if (key.endsWith(".rank")) mission.getSorcerers().get(index).setRank(value);
                }

                if (key.startsWith("technique[")) {
                    int index = extractIndex(key);
                    while (mission.getTechniques().size() <= index) {
                        mission.getTechniques().add(new Technique());
                    }
                    
                    Technique currentTech = mission.getTechniques().get(index);
                    
                    if (key.endsWith(".name")) currentTech.setName(value);
                    if (key.endsWith(".type")) currentTech.setType(value);
                    if (key.endsWith(".damage")) currentTech.setDamage(Long.parseLong(value));
                    
                    if (key.endsWith(".owner")) {
                        currentTech.setOwner(new Sorcerer(value));
                    }
                }
            }
            return mission;
        } catch (Exception e) {
            return null;
        }
    }

    private int extractIndex(String key) {
        return Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));
    }
}

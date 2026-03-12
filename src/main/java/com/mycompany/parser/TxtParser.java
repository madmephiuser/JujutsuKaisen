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


public class TxtParser implements MissionParser{

    public Mission parse(String filePath) {
    try {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Mission mission = new Mission();
        mission.curse = new Curse();
        mission.sorcerers = new ArrayList<>();
        mission.techniques = new ArrayList<>();

        for (String line : lines) {
            if (!line.contains(":")) continue;
            String[] parts = line.split(":", 2);
            String key = parts[0].trim();
            String value = parts[1].trim();

            if (key.equals("missionId")) mission.missionId = value;
            if (key.equals("location")) mission.location = value;
            if (key.equals("outcome")) mission.outcome = value;
            if (key.equals("damageCost")) mission.damageCost = Long.parseLong(value);

            if (key.startsWith("curse.")) {
                if (key.endsWith(".name")) mission.curse.name = value;
                if (key.endsWith(".threatLevel")) mission.curse.threatLevel = value;
            }

            if (key.startsWith("sorcerer[")) {
                int index = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));
                while (mission.sorcerers.size() <= index) mission.sorcerers.add(new Sorcerer());              
                if (key.endsWith(".name")) mission.sorcerers.get(index).name = value;
                if (key.endsWith(".rank")) mission.sorcerers.get(index).rank = value;
            }
            if (key.startsWith("technique[")) {
                int index = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));
                while (mission.techniques.size() <= index) mission.techniques.add(new Technique());
                
                if (key.endsWith(".name")) mission.techniques.get(index).name = value;
                if (key.endsWith(".type")) mission.techniques.get(index).type = value;
                if (key.endsWith(".damage")) mission.techniques.get(index).damage = Long.parseLong(value);
            }
        }
        return mission;
    } catch (Exception e) {
        System.out.println("Ошибка в структуре TXT " + e.getMessage());
        return null;
    }
}
}

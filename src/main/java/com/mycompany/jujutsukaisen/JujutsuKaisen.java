/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;

import com.mycompany.parser.*;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class JujutsuKaisen {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nВведите путь к файлу миссии (или 'exit' для выхода):");
            String path = scanner.nextLine().trim();

            if (path.equalsIgnoreCase("exit")) {
                System.out.println("Выход");
                break;
            }

            MissionParser parser = FactoryParser.getParser(path);
            if (parser == null) {
                System.out.println("Ошибка неподдерживаемый формат файла");
                continue;
            }
            Mission mission = parser.parse(path);
            if (mission != null) {
                linkST(mission);
                MissionRenderer.showMission(mission);
            } else {
                System.out.println("Ошибка не удалось загрузить данные.");
            }
        }
    }

    private static void linkST(Mission m) {
        if (m.getTechniques() == null || m.getSorcerers() == null) return;

        for (Technique tech : m.getTechniques()) {
            if (tech.getOwner() != null) {
                String nameToFind = tech.getOwner().getName();
                for (Sorcerer s : m.getSorcerers()) {
                    if (s.getName().equals(nameToFind)) {
                        tech.setOwner(s); 
                        break;
                    }
                }
            }
        }
    }
}
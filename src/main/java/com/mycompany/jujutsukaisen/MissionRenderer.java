/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;

/**
 *
 * @author Professional
 */
public class MissionRenderer {
    public static void showMission(Mission m) {
        if (m == null) return;
        System.out.println("\nИнформации о миссии");
        System.out.println("ID: " + m.missionId);
        System.out.println("Локация: " + m.location);
        System.out.println("Итог: " + m.outcome);
        System.out.println("\nУчастники:");
        if (m.sorcerers != null) {
            for (Sorcerer s : m.sorcerers) {
                System.out.println(" " + s.name + " (" + s.rank + ")");
            }
        }
        if (m.curse != null) {
            System.out.println("\nЦель:");
            System.out.println("- " + m.curse.name + " [" + m.curse.threatLevel + "]");
        }
        System.out.println("\nМагические техники:");
        if (m.techniques != null) {
            for (Technique t : m.techniques) {
                System.out.println(" " + t.name + " (Урон: " + t.damage + ")");
            }
        }

        if (m.comment != null) {
            System.out.println("\nПримечание: " + m.comment);
        }
    }
}

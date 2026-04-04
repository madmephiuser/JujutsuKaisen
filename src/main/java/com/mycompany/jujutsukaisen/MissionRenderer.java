/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;


public class MissionRenderer {
    public static void showMission(Mission m) {
        if (m == null) return;
        System.out.println("ID: " + m.getMissionId());
        System.out.println("дата: " + m.getDate());
        System.out.println("Локация: " + m.getLocation());
        System.out.println("Итог: " + m.getOutcome());
        System.out.println("Ущерб: " + m.getDamageCost());

        if (m.getSorcerers() != null) {
            System.out.println("\nУчастники:");
            for (Sorcerer s : m.getSorcerers()) {
                System.out.println(s.getName() + " (Ранг: " + s.getRank() + ")");
            }
        }

        if (m.getTechniques() != null) {
            System.out.println("\nИспользованные техники:");
            for (Technique t : m.getTechniques()) {
                String ownerName = (t.getOwner() != null) ? t.getOwner().getName() : "Не указан";
                System.out.println(t.getName() + " тип: " + t.getType() + " Владелец: " + ownerName + " Урон: " + t.getDamage());
            }
        }
        if (m.getNotes() != null) {
            System.out.println("\nПримечание: " + m.getNotes());
        }
    }
}
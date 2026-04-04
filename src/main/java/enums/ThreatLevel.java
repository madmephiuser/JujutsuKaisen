/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

public enum ThreatLevel {
    LOW, MEDIUM, HIGH, SPECIAL_GRADE;

    public static ThreatLevel fromString(String text) {
        if (text == null) return null;
        String val = text.trim().toUpperCase();
        if (val.equals("SPECIAL")) return SPECIAL_GRADE; 
        try {
            return valueOf(val.replace(" ", "_"));
        } catch (Exception e) {
            return null;
        }
    }
}


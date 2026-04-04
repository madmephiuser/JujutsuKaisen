/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

public enum TechniqueType {
    INNATE, SHIKIGAMI, BARRIER, WEAPON, BODY, UNKNOWN;

    public static TechniqueType fromString(String text) {
        try {
            return valueOf(text.trim().toUpperCase());
        } catch (Exception e) {
            return UNKNOWN; 
        }
    }  
}

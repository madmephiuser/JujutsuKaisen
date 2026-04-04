/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

public enum MissionOutcome {
    SUCCESS, FAILURE, PARTIAL_SUCCESS;
    
    public static MissionOutcome fromString(String text) {
        if (text == null) return null;
        try {
            return valueOf(text.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; 
        }
    }
}

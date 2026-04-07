/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

public enum Mobility {
    LOW, STATIC, HIGH ;
    
    public static Mobility fromString(String t) {
        try {
            return valueOf(t.trim().toUpperCase()); 
        } catch (Exception e) {
            return null; 
        }
    }  
}

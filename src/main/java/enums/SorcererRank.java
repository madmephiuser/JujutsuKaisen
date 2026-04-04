/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

public enum SorcererRank {
    GRADE_4, GRADE_3, GRADE_2, GRADE_1, SPECIAL_GRADE, SEMI_GRADE_1;
    
    public static SorcererRank fromString(String text) {
        if (text == null) return null;
        try {
            String formatted = text.trim().toUpperCase().replace(" ", "_");
            return valueOf(formatted);
        } catch (IllegalArgumentException e) {
            return text.toUpperCase().contains("SEMI") ? SEMI_GRADE_1 : null;
        }
    }
}

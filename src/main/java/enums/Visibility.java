
package enums;

public enum Visibility {
    CLEAR, POOR;
    
    public static Visibility fromString(String t) {
        try {
            return valueOf(t.trim().toUpperCase()); 
        } catch (Exception e) {
            return null; 
        }
    }
}


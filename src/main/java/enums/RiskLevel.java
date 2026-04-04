
package enums;

public enum RiskLevel {
   LOW, MEDIUM, HIGH;
   
   public static RiskLevel fromString(String t) {
        try { return valueOf(t.trim().toUpperCase()); 
        } catch (Exception e) {
            return null; 
        }
    }
}

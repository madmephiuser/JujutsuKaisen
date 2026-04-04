
package com.mycompany.jujutsukaisen;

import enums.Mobility;
import enums.RiskLevel;

public class EnemyActivity {
    private String behaviorType;
    private String targetPriority; 
    private String attackPatterns; 
    private Mobility mobility; 
    private RiskLevel escalationRisk; 

    public String getBehaviorType() {
        return behaviorType; 
    }
    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType; 
    }

    public String getTargetPriority() {
        return targetPriority; 
    }
    public void setTargetPriority(String targetPriority) {
        this.targetPriority = targetPriority; 
    }

    public String getAttackPatterns() {
        return attackPatterns; 
    }
    public void setAttackPatterns(String attackPatterns) {
        this.attackPatterns = attackPatterns; 
    }

    public Mobility getMobility() {
        return mobility; 
    }
    public void setMobility(Mobility mobility) {
        this.mobility = mobility; 
    }

    public RiskLevel getEscalationRisk() {
        return escalationRisk; 
    }
    public void setEscalationRisk(RiskLevel escalationRisk) {
        this.escalationRisk = escalationRisk; 
    }
}

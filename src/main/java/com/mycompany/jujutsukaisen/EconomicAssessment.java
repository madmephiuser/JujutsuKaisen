
package com.mycompany.jujutsukaisen;

import jakarta.persistence.Embeddable;

@Embeddable
public class EconomicAssessment {
    private Double totalDamageCost;
    private Double infrastructureDamage;
    private Double commercialDamage;
    private Double transportDamage;
    private Integer recoveryEstimateDays;
    private Boolean insuranceCovered;
    
    public EconomicAssessment(){ 
    }
    
    public Double getTotalDamageCost() {
        return totalDamageCost; 
    }
    public void setTotalDamageCost(Double totalDamageCost) {
        this.totalDamageCost = totalDamageCost; 
    }

    public Double getInfrastructureDamage() {
        return infrastructureDamage; 
    }
    public void setInfrastructureDamage(Double infrastructureDamage) {
        this.infrastructureDamage = infrastructureDamage; 
    }

    public Double getCommercialDamage() {
        return commercialDamage; 
    }
    public void setCommercialDamage(Double commercialDamage) {
        this.commercialDamage = commercialDamage; 
    }

    public Double getTransportDamage() {
        return transportDamage; 
    }
    public void setTransportDamage(Double transportDamage) {
        this.transportDamage = transportDamage; 
    }

    public Integer getRecoveryEstimateDays() {
        return recoveryEstimateDays; 
    }
    public void setRecoveryEstimateDays(Integer recoveryEstimateDays) {
        this.recoveryEstimateDays = recoveryEstimateDays; 
    }

    public Boolean getInsuranceCovered() {
        return insuranceCovered; 
    }
    public void setInsuranceCovered(Boolean insuranceCovered) {
        this.insuranceCovered = insuranceCovered; }
}

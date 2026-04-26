
package com.mycompany.jujutsukaisen;

import enums.Visibility;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnvironmentConditions {
    private String weather;
    private String timeOfDay;
    private Visibility visibility;
    private Double cursedEnergyDensity;

    public String getWeather() {
        return weather; 
    }
    public void setWeather(String w) {
        this.weather = w; 
    }
    public String getTimeOfDay() {
        return timeOfDay; 
    }
    public void setTimeOfDay(String t) {
        this.timeOfDay = t; 
    }
    public Visibility getVisibility() {
        return visibility; 
    }
    public void setVisibility(Visibility v) {
        this.visibility = v; 
    }
    public Double getCursedEnergyDensity() {
        return cursedEnergyDensity; 
    }
    public void setCursedEnergyDensity(Double d) { 
        this.cursedEnergyDensity = d; 
    }
}

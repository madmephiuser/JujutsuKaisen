package com.mycompany.jujutsukaisen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Curse {
    private String name;
    private String threatLevel;

    public Curse() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getThreatLevel() { return threatLevel; }
    public void setThreatLevel(String threatLevel) { this.threatLevel = threatLevel; }
}
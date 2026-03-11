package com.mycompany.jujutsukaisen;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Mission {
    public String missionId;
    public String date;
    public String location;
    public String outcome;
    public long damageCost;
    
    public Curse curse;
    public List<Sorcerer> sorcerers;
    public List<Technique> techniques;
    @JsonAlias({"comment", "note"})
    public String comment;
    
    public Mission() {
    }
}

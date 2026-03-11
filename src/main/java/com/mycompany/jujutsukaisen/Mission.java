/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.jujutsukaisen;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Mission {
    public String missionId;
    public String date;
    public String location;
    public String outcome;
    public long damageCost;
    
    public Curse curse;
    public List<Sorcerers> sorcerers;
    public List<Technique> techniques;
    public String comment;
    
    public Mission() {
    }
}

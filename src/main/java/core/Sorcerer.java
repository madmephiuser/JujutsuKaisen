package core;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sorcerer {
    public String name;
    public String rank;
    
    public Sorcerer(){
    }
}


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class Technique {
    public String name;
    public String type;
    public String owner;
    public long damage;
    
    public Technique(){
    }
}

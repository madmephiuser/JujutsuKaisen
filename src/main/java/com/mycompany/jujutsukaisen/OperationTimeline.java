
package com.mycompany.jujutsukaisen;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_timelines")
public class OperationTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;  
    private String type;
    private String description;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public OperationTimeline() {}

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Mission getMission() {
        return mission;
    }

    public String getTimestamp() {
        return timestamp; 
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp; 
    }

    public String getType() {
        return type; 
    }
    public void setType(String type) {
        this.type = type; 
    }

    public String getDescription() {
        return description; 
    }
    public void setDescription(String description) {
        this.description = description; 
    }
}

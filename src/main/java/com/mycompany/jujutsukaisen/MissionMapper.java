
package com.mycompany.jujutsukaisen;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class MissionMapper {

    public Mission mapToEntity(Mission mission) {
        if (mission == null) return null;
        if (mission.getSorcerers() != null) {
            mission.getSorcerers().forEach(s -> s.setMission(mission));
        }
        
        if (mission.getTechniques() != null) {
            mission.getTechniques().forEach(t -> t.setMission(mission));
        }
        
        if (mission.getOperationTimeline() != null) {
            mission.getOperationTimeline().forEach(ot -> ot.setMission(mission));
        }

        if (mission.getSorcerers() == null) {
            mission.setSorcerers(new ArrayList<>());
        }
        if (mission.getTechniques() == null) {
            mission.setTechniques(new ArrayList<>());
        }
        if (mission.getOperationTimeline() == null) {
            mission.setOperationTimeline(new ArrayList<>());
        }
        if (mission.getOperationTags() == null) {
            mission.setOperationTags(new ArrayList<>());
        }
        if (mission.getSupportUnits() == null) {
            mission.setSupportUnits(new ArrayList<>());
        }
        if (mission.getRecommendations() == null) {
            mission.setRecommendations(new ArrayList<>());
        }
        if (mission.getArtifactsRecovered() == null) {
            mission.setArtifactsRecovered(new ArrayList<>());
        }
        if (mission.getEvacuationZones() == null) {
            mission.setEvacuationZones(new ArrayList<>());
        }
        if (mission.getStatusEffects() == null) {
            mission.setStatusEffects(new ArrayList<>());
        }

        return mission;
    }
}

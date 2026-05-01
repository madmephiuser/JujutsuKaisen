
package com.mycompany.jujutsukaisen;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class MissionMapper {

    public Mission mapToEntity(Mission mission) {
        if (mission == null) return null;

        if (mission.getSorcerers() == null) mission.setSorcerers(new ArrayList<>()); 
        mission.getSorcerers().forEach(s -> s.setMission(mission));
        mission.getTechniques().forEach(t -> t.setMission(mission));
        mission.getOperationTimeline().forEach(ot -> ot.setMission(mission));

        return mission;
    }
}

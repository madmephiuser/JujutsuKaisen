package service;

import com.mycompany.jujutsukaisen.Mission;
import repository.MissionRepository;
import com.mycompany.parser.FactoryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Transactional
    public Mission importMission(String filePath) {
        Mission mission = FactoryParser.getParser().parse(filePath);
        if (mission != null) {
            return missionRepository.save(mission);
        }
        return null;
    }

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Optional<Mission> getMissionById(String id) {
        return missionRepository.findById(id);
    }
}

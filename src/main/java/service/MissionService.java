package service;

import com.mycompany.jujutsukaisen.Mission;
import repository.MissionRepository;
import com.mycompany.parser.FactoryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Transactional
    public Mission importMission(String filePath) {
        Mission mission = FactoryParser.getParser().parse(filePath);

        if (mission != null) {
            if (missionRepository.existsById(mission.getMissionId())) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Отчет с id " + mission.getMissionId() + " уже есть в базе"
                );
            }
            if (mission.getSorcerers() != null) {
                mission.getSorcerers().forEach(s -> s.setMission(mission));
            }
            if (mission.getTechniques() != null) {
                mission.getTechniques().forEach(t -> t.setMission(mission));
            }

            return missionRepository.save(mission);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Не удалось распарсить файл");
    }

    @Transactional(readOnly = true)
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mission getMissionById(String id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Миссия с id " + id + " не найдена"
                ));
    }
    
    @Transactional
    public void deleteMissionById(String id) {
        if (!missionRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Невозможно удалить: миссия с id   " + id + " не найдена"
            );
        }
        missionRepository.deleteById(id);
    }
}
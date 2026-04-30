package service;

import com.mycompany.jujutsukaisen.Mission;
import com.mycompany.jujutsukaisen.MissionMapper;
import repository.MissionRepository;
import com.mycompany.parser.FactoryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import validate.ControlValidator;
import validate.ValidationException;
import validate.MissionException;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionMapper missionMapper;
    @Transactional
    public Mission importMissionFromFile(MultipartFile file) {
        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            String fileName = file.getOriginalFilename();
            String tempPath = tempDir + File.separator + fileName;
            
            File tempFile = new File(tempPath);
            file.transferTo(tempFile);

            Mission mission = FactoryParser.getParser().parse(tempPath);
            tempFile.delete();
            
            if (mission == null) {
                throw new MissionException("Не удалось распарсить файл", "PARSE_ERROR");
            }

            try {
                ControlValidator validator = new ControlValidator();
                validator.validate(mission);
            } catch (ValidationException e) {
                throw new MissionException(e.getMessage(), "VALIDATION_ERROR");
            }

            if (missionRepository.existsById(mission.getMissionId())) {
                throw new MissionException("Отчет с id " + mission.getMissionId() + " уже есть в базе", "DUPLICATE_ERROR");
            }

            mission = missionMapper.mapToEntity(mission);
            return missionRepository.save(mission);
            
        } catch (IOException e) {
            throw new MissionException("Ошибка при сохранении файла: " + e.getMessage(), "IO_ERROR");
        }
    }

    @Transactional
    public Mission importMission(String filePath) {
        Mission mission = FactoryParser.getParser().parse(filePath);

        if (mission == null) {
            throw new MissionException("Не удалось распарсить файл. Проверьте формат.", "PARSE_ERROR");
        }

        try {
            ControlValidator validator = new ControlValidator();
            validator.validate(mission);
        } catch (ValidationException e) {
            throw new MissionException(e.getMessage(), "VALIDATION_ERROR");
        }

        if (missionRepository.existsById(mission.getMissionId())) {
            throw new MissionException("Отчет с id " + mission.getMissionId() + " уже есть в базе", "DUPLICATE_ERROR");
        }

        mission = missionMapper.mapToEntity(mission);
        return missionRepository.save(mission);
    }
    
    @Transactional(readOnly = true)
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mission getMissionById(String id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new MissionException("Миссия с id " + id + " не найдена", "NOT_FOUND"));
    }

    @Transactional
    public void deleteMissionById(String id) {
        if (!missionRepository.existsById(id)) {
            throw new MissionException("Невозможно удалить: миссия с id " + id + " не найдена", "NOT_FOUND");
        }
        missionRepository.deleteById(id);
    }
}
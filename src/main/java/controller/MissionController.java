
package controller;

import com.mycompany.jujutsukaisen.Mission;
import java.util.List;
import service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @PostMapping("/import")
    public Mission importFile(@RequestParam String path) {
        return missionService.importMission(path);
    }

    @GetMapping
    public List<Mission> getAll() {
        return missionService.getAllMissions();
    }

    @GetMapping("/{id}")
    public Mission getOne(@PathVariable String id) {
        return missionService.getMissionById(id).orElse(null);
    }
}

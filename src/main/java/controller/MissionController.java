
package controller;

import com.mycompany.jujutsukaisen.Mission;
import com.mycompany.jujutsukaisen.Mission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import report.DetailedReport;
import report.ShortReport;
import service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@Tag(name = "Архив магических миссий", description = "Управление отчетами о магических операциях и проклятиях")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private DetailedReport detailedReport;

    @Autowired
    private ShortReport shortReport;

    @GetMapping
    @Operation(summary = "получить список всех миссий")
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @PostMapping("/import")
    @Operation(summary = "загрузить отчет миссии")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Миссия успешно создана"),
        @ApiResponse(responseCode = "409", description = "Отчет с таким id уже существует")
    })
    public ResponseEntity<Mission> importMission(@RequestParam String path) {
        return new ResponseEntity<>(missionService.importMission(path), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "поиск миссии по id")
    public Mission getMissionById(@PathVariable String id) {
        return missionService.getMissionById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "удаление миссии по id")
    public ResponseEntity<String> deleteMission(@PathVariable String id) {
        missionService.deleteMissionById(id);
        return ResponseEntity.ok("Миссия " + id + " успешно удалена из архива.");
    }

    @GetMapping("/{id}/report")
    @Operation(summary = "получить отчет")
    public String getMissionReport(
            @PathVariable String id, 
            @RequestParam(required = false, defaultValue = "false") boolean full) {

        Mission mission = missionService.getMissionById(id);
        return full ? detailedReport.generate(mission) : shortReport.generate(mission);
    }
}
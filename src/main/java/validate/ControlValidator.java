/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

import com.mycompany.jujutsukaisen.*;
import java.util.List;


public class ControlValidator extends Validator<Mission> {

    @Override
    public void validate(Mission mission) throws ValidationException {
        if (mission == null) throw new ValidationException("Объект миссии не может быть null");

        checkRequired(mission.getMissionId(), "missionId");
        checkRequired(mission.getDate(), "date");
        checkRequired(mission.getLocation(), "location");
        if (mission.getOutcome() == null) {
            throw new ValidationException("Поле 'outcome' не должно быть пустым");
        }

        if (mission.getDamageCost() != null && mission.getDamageCost() < 0 ) {
            throw new ValidationException("Ущерб (damageCost) не может быть отрицательным");
        }

        if (mission.getCurse() == null) {
            throw new ValidationException("Блок данных 'curse' не должно быть пустым");
        } else {
            checkRequired(mission.getCurse().getName(), "curse.name");
            if (mission.getCurse().getThreatLevel() == null) {
                throw new ValidationException("Уровень угрозы проклятия (threatLevel) обязателен");
            }
        }

        validateSorcerers(mission.getSorcerers());
        validateTechniques(mission.getTechniques(), mission.getSorcerers());
        validateTimeline(mission.getOperationTimeline());

        callNext(mission);
    }

    private void validateSorcerers(List<Sorcerer> sorcerers) throws ValidationException {
        if (sorcerers != null) {
            for (Sorcerer s : sorcerers) {
                if (s == null) continue;
                checkRequired(s.getName(), "sorcerers.name");
                if (s.getRank() == null) {
                    throw new ValidationException("Для участника " + s.getName() + " должен быть указан ранг");
                }
            }
        }
    }

    private void validateTechniques(List<Technique> techniques, List<Sorcerer> sorcerers) throws ValidationException {
        if (techniques == null || techniques.isEmpty()) return;

        for (Technique tech : techniques) {
            if (tech == null) continue;
            checkRequired(tech.getName(), "techniques.name");
            if (tech.getType() == null) throw new ValidationException("Тип техники не указан для: " + tech.getName());
            checkRequired(tech.getOwner(), "techniques.owner");

            String ownerName = tech.getOwner().trim();
            boolean found = false;
            if (sorcerers != null) {
                for (Sorcerer s : sorcerers) {
                    if (s != null && s.getName() != null && s.getName().trim().equalsIgnoreCase(ownerName)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                throw new ValidationException("Техника '" + tech.getName() + "' закреплена за магом '" 
                    + ownerName + "', который отсутствует в списке участников миссии!");
            }
        }
    }

    private void validateTimeline(List<OperationTimeline> timeline) throws ValidationException {
        if (timeline != null) {
            for (OperationTimeline ot : timeline) {
                if (ot == null) continue;
                checkRequired(ot.getTimestamp(), "operationTimeline.timestamp");
                checkRequired(ot.getDescription(), "operationTimeline.description");
                if (ot.getType() == null) throw new ValidationException("Тип события в хронологии не указан");
            }
        }
    }

    private void checkRequired(String value, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("Поле '" + fieldName + "' обязательно для заполнения");
        }
    }
}

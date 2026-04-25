
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

        if (mission.getDamageCost() != null && mission.getDamageCost() < 0) {
            throw new ValidationException("Ущерб не может быть отрицательным");
        }

        if (mission.getCurse() == null) {
            throw new ValidationException("Блок 'curse' обязателен");
        } else {
            checkRequired(mission.getCurse().getName(), "curse.name");
            if (mission.getCurse().getThreatLevel() == null) {
                throw new ValidationException("Уровень угрозы обязателен");
            }
        }

        validateSorcerers(mission.getSorcerers());
        validateTechniques(mission.getTechniques(), mission.getSorcerers());
        validateTimeline(mission.getOperationTimeline());

        callNext(mission);
    }

    private void validateSorcerers(List<Sorcerer> sorcerers) throws ValidationException {
        if (sorcerers == null) return;
        for (Sorcerer s : sorcerers) {
            if (s == null) continue;
            checkRequired(s.getName(), "sorcerers.name");
            if (s.getRank() == null) throw new ValidationException("Ранг участника обязателен");
        }
    }

    private void validateTechniques(List<Technique> techniques, List<Sorcerer> sorcerers) throws ValidationException {
        if (techniques == null || techniques.isEmpty()) return;
        for (Technique tech : techniques) {
            if (tech == null) continue;
            checkRequired(tech.getName(), "techniques.name");
            if (tech.getType() == null) throw new ValidationException("Тип техники обязателен");
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
            if (!found) throw new ValidationException("Владелец техники не найден в списке участников");
        }
    }

    private void validateTimeline(List<OperationTimeline> timeline) throws ValidationException {
        if (timeline == null) return;
        for (OperationTimeline ot : timeline) {
            if (ot == null) continue;
            checkRequired(ot.getTimestamp(), "timeline.timestamp");
            checkRequired(ot.getDescription(), "timeline.description");
            if (ot.getType() == null) throw new ValidationException("Тип события обязателен");
        }
    }

    private void checkRequired(String value, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("Поле '" + fieldName + "' обязательно");
        }
    }
}
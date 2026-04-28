
package report;

import com.mycompany.jujutsukaisen.Mission;
import org.springframework.stereotype.Component;

@Component
public class ShortReport implements IReport {
    @Override
    public String generate(Mission m) {
        if (m == null) return "Миссия не найдена.";
        return String.format(
            "КРАТКИЙ ОТЧЕТ\nID: %s\nДата: %s\nЛокация: %s\nИтог: %s\nУщерб: %d",
            m.getMissionId(), m.getDate(), m.getLocation(), m.getOutcome(), m.getDamageCost()
        );
    }
}

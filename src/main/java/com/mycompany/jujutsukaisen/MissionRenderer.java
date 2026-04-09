
package com.mycompany.jujutsukaisen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MissionRenderer implements MissionDisplay {

    @Override
    public void display (Mission m) {
        if (m == null) return;
        JFrame frame = new JFrame("Отчет по миссии: " + m.getMissionId());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        StringBuilder sb = new StringBuilder();

        sb.append("ID МИССИИ: ").append(m.getMissionId()).append("\n");
        sb.append("Дата: ").append(m.getDate()).append("\n");
        sb.append("Локация: ").append(m.getLocation()).append("\n");
        sb.append("Итог: ").append(m.getOutcome()).append("\n");
        renderField(sb, "Общая оценка ущерба: ", m.getDamageCost());

        if (m.getCurse() != null) {
            sb.append("\nПроклятия\n");
            sb.append("Название: ").append(m.getCurse().getName()).append("\n");
            sb.append("Уровень угрозы: ").append(m.getCurse().getThreatLevel()).append("\n");
        }

        if (hasData(m.getSorcerers())) {
            sb.append("\nУчастники\n");
            for (Sorcerer s : m.getSorcerers()) {
                sb.append("- ").append(s.getName());
                if (s.getRank() != null) sb.append(" (Ранг: ").append(s.getRank()).append(")");
                sb.append("\n");
            }
        }

        if (hasData(m.getTechniques())) {
            sb.append("\nТехники\n");
            for (Technique t : m.getTechniques()) {
                sb.append("- ").append(t.getName()).append(" (").append(t.getType()).append(")\n");
                renderField(sb, "  Владелец: ", t.getOwner());
                renderField(sb, "  Урон: ", t.getDamage());
            }
        }

        if (m.getEconomicAssessment() != null) {
            EconomicAssessment ec = m.getEconomicAssessment();
            sb.append("\nЭкономическая оценка ущерба\n");
            renderField(sb, "Общий ущерб: ", ec.getTotalDamageCost());
            renderField(sb, "Ущерб инфраструктуре: ", ec.getInfrastructureDamage());
            renderField(sb, "Коммерческий ущерб: ", ec.getCommercialDamage());
            renderField(sb, "Ущерб транспорту: ", ec.getTransportDamage());
            renderField(sb, "Время восстановления: ", ec.getRecoveryEstimateDays());
            if (ec.getInsuranceCovered() != null) sb.append("Страховка: ").append(ec.getInsuranceCovered() ? "Да" : "Нет").append("\n");
        }

        if (m.getCivilianImpact() != null) {
            CivilianImpact ci = m.getCivilianImpact();
            sb.append("\nВлияние на граждан\n");
            renderField(sb, "Эвакуировано: ", ci.getEvacuated());
            renderField(sb, "Пострадавшие: ", ci.getInjured());
            renderField(sb, "Пропавшие: ", ci.getMissing());
            renderField(sb, "Риск раскрытия: ", ci.getPublicExposureRisk());
        }

        if (m.getEnemyActivity() != null) {
            EnemyActivity en = m.getEnemyActivity();
            sb.append("\nПоведение противника\n");
            renderField(sb, "Тип поведения: ", en.getBehaviorType());
            renderList(sb, "Приоритет целей: ", en.getTargetPriority());
            renderList(sb, "Паттерны атак: ", en.getAttackPatterns());
            renderField(sb, "Мобильность: ", en.getMobility());
            renderField(sb, "Риск эскалации: ", en.getEscalationRisk());
        }

        if (m.getEnvironmentConditions() != null) {
            EnvironmentConditions env = m.getEnvironmentConditions();
            sb.append("\nУсловия среды\n");
            renderField(sb, "Погода: ", env.getWeather());
            renderField(sb, "Время суток: ", env.getTimeOfDay());
            renderField(sb, "Видимость: ", env.getVisibility());
            renderField(sb, "Плотность энергии: ", env.getCursedEnergyDensity());
        }

        if (hasData(m.getOperationTimeline())) {
            sb.append("\nХронология\n");
            for (OperationTimeline ot : m.getOperationTimeline()) {
                sb.append(ot.getTimestamp()).append(" | ").append(ot.getType()).append(": ").append(ot.getDescription()).append("\n");
            }
        }
        
        renderList(sb, "Теги: ", m.getOperationTags());
        renderList(sb, "Вспомогательные силы: ", m.getSupportUnits());
        renderList(sb, "Рекомендации: ", m.getRecommendations());
        renderField(sb, "Примечание: ", m.getNotes());
        renderList(sb, "Артефакты: ", m.getArtifactsRecovered());
        renderList(sb, "Зоны эвакуации: ", m.getEvacuationZones());
        renderList(sb, "Эффекты: ", m.getStatusEffects());

        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        ta.setMargin(new Insets(10, 10, 10, 10));
        frame.add(new JScrollPane(ta));
        frame.setSize(650, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void renderField(StringBuilder sb, String label, Object value) {
        if (value != null && !value.toString().isEmpty()) sb.append(label).append(value).append("\n");
    }

    private void renderList(StringBuilder sb, String label, List<String> list) {
        if (list != null && !list.isEmpty()) sb.append(label).append(String.join(", ", list)).append("\n");
    }

    private boolean hasData(List<?> list) {
        return list != null && !list.isEmpty();
    }
}

package com.mycompany.jujutsukaisen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MissionRenderer implements MissionDisplay {

    @Override
    public void display(Mission m) {
        if (m == null) return;
        StringBuilder sb = new StringBuilder();

        sb.append("ID МИССИИ: ").append(m.getMissionId()).append("\n");
        sb.append("Дата: ").append(m.getDate()).append("\n");
        sb.append("Локация: ").append(m.getLocation()).append("\n");
        sb.append("Итог: ").append(m.getOutcome()).append("\n");
        renderNumericField(sb, "Общая оценка ущерба: ", m.getDamageCost());

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
                renderNumericField(sb, "  Урон: ", t.getDamage());
            }
        }
        
        EconomicAssessment ec = m.getEconomicAssessment();
        if (ec != null) {
            StringBuilder ecSb = new StringBuilder();
            renderNumericField(ecSb, "Общий ущерб: ", ec.getTotalDamageCost());
            renderNumericField(ecSb, "Ущерб инфраструктуре: ", ec.getInfrastructureDamage());
            renderNumericField(ecSb, "Коммерческий ущерб: ", ec.getCommercialDamage());
            renderNumericField(ecSb, "Ущерб транспорту: ", ec.getTransportDamage());
            renderNumericField(ecSb, "Время восстановления (дн): ", ec.getRecoveryEstimateDays());
            if (ec.getInsuranceCovered() != null) {
                ecSb.append("Страховка: ").append(ec.getInsuranceCovered() ? "Да" : "Нет").append("\n");
            }
            if (ecSb.length() > 0) {
                sb.append("\nЭкономическая оценка ущерба\n").append(ecSb);
            }
        }

        CivilianImpact ci = m.getCivilianImpact();
        if (ci != null) {
            StringBuilder ciSb = new StringBuilder();
            renderNumericField(ciSb, "Эвакуировано: ", ci.getEvacuated());
            renderNumericField(ciSb, "Пострадавшие: ", ci.getInjured());
            renderNumericField(ciSb, "Пропавшие: ", ci.getMissing());
            renderField(ciSb, "Риск раскрытия: ", ci.getPublicExposureRisk());
            if (ciSb.length() > 0) {
                sb.append("\nВлияние на граждан\n").append(ciSb);
            }
        }

        EnemyActivity en = m.getEnemyActivity();
        if (en != null) {
            StringBuilder enSb = new StringBuilder();
            renderField(enSb, "Тип поведения: ", en.getBehaviorType());
            renderList(enSb, "Приоритет целей: ", en.getTargetPriority());
            renderList(enSb, "Паттерны атак: ", en.getAttackPatterns());
            renderField(enSb, "Мобильность: ", en.getMobility());
            renderField(enSb, "Риск эскалации: ", en.getEscalationRisk());
            if (enSb.length() > 0) {
                sb.append("\nПоведение противника\n").append(enSb);
            }
        }

        EnvironmentConditions env = m.getEnvironmentConditions();
        if (env != null) {
            StringBuilder envSb = new StringBuilder();
            renderField(envSb, "Погода: ", env.getWeather());
            renderField(envSb, "Время суток: ", env.getTimeOfDay());
            renderField(envSb, "Видимость: ", env.getVisibility());
            renderNumericField(envSb, "Плотность энергии: ", env.getCursedEnergyDensity());
            if (envSb.length() > 0) {
                sb.append("\nУсловия среды\n").append(envSb);
            }
        }

        if (hasData(m.getOperationTimeline())) {
            sb.append("\nХронология\n");
            for (OperationTimeline ot : m.getOperationTimeline()) {
                sb.append(ot.getTimestamp()).append(" | ")
                  .append(ot.getType()).append(": ")
                  .append(ot.getDescription()).append("\n");
            }
        }

        renderList(sb, "Теги: ", m.getOperationTags());
        renderList(sb, "Вспомогательные силы: ", m.getSupportUnits());
        renderList(sb, "Рекомендации: ", m.getRecommendations());
        renderField(sb, "Примечание: ", m.getNotes());
        renderList(sb, "Найденные артефакты: ", m.getArtifactsRecovered());
        renderList(sb, "Зоны эвакуации: ", m.getEvacuationZones());
        renderList(sb, "Эффекты: ", m.getStatusEffects());

        showGui(m.getMissionId(), sb.toString());
    }

    private void renderField(StringBuilder sb, String label, Object value) {
        if (value != null && !value.toString().trim().isEmpty()) {
            sb.append(label).append(value).append("\n");
        }
    }

    private void renderNumericField(StringBuilder sb, String label, Number value) {
        if (value != null && value.doubleValue() > 0) {
            sb.append(label).append(value).append("\n");
        }
    }

    private void renderList(StringBuilder sb, String label, List<String> list) {
        if (hasData(list)) {
            sb.append(label).append(String.join(", ", list)).append("\n");
        }
    }

    private boolean hasData(List<?> list) {
        return list != null && !list.isEmpty();
    }

    private void showGui(String id, String text) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Отчет по миссии: " + id);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JTextArea ta = new JTextArea(text);
            ta.setEditable(false);
            frame.add(new JScrollPane(ta));
            frame.setSize(650, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
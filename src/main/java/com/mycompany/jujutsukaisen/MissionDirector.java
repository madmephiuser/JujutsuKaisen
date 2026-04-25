package com.mycompany.jujutsukaisen;

public class MissionDirector {
    private final Builder builder;

    public MissionDirector(Builder builder) {
        this.builder = builder;
    }

    public void startNewMission() {
        builder.createNewMission();
    }

    public void processData(String section, String key, String value) {
        String event = section.toUpperCase();
        
        if ("rawLine".equals(key)) {
            String[] t = value.split("\\|");
            for (int i = 0; i < t.length; i++) t[i] = t[i].trim();

            switch (event) {
                case "MISSION_CREATED" -> {
                    if (t.length > 1) builder.buildBaseInfo("missionId", t[1]);
                    if (t.length > 2) builder.buildBaseInfo("date", t[2]);
                    if (t.length > 3) builder.buildBaseInfo("location", t[3]);
                }
                case "CURSE_DETECTED" -> {
                    if (t.length > 1) builder.buildCurse("name", t[1]);
                    if (t.length > 2) builder.buildCurse("threatLevel", t[2]);
                }
                case "SORCERER_ASSIGNED" -> {
                    if (t.length > 1) builder.buildSorcerer("name", t[1]);
                    if (t.length > 2) builder.buildSorcerer("rank", t[2]);
                }
                case "TECHNIQUE_USED" -> {
                    if (t.length > 1) builder.buildTechnique("name", t[1]);
                    if (t.length > 2) builder.buildTechnique("type", t[2]);
                    if (t.length > 3) builder.buildTechnique("owner", t[3]);
                    if (t.length > 4) builder.buildTechnique("damage", t[4]);
                }
                case "ECONOMIC_ASSESSMENT" -> {
                    if (t.length > 1) builder.buildEconomic("totalDamageCost", t[1]);
                    if (t.length > 2) builder.buildEconomic("infrastructureDamage", t[2]);
                    if (t.length > 3) builder.buildEconomic("insuranceCovered", t[3]);
                }
                case "CIVILIAN_IMPACT" -> {
                    if (t.length > 1) builder.buildCivilian("evacuated", t[1]);
                    if (t.length > 2) builder.buildCivilian("injured", t[2]);
                    if (t.length > 3) builder.buildCivilian("publicExposureRisk", t[3]);
                }
                case "ENEMY_ACTION" -> {
                    if (t.length > 1) builder.buildEnemy("behaviorType", t[1]);
                    if (t.length > 2) builder.buildEnemy("mobility", t[2]);
                    if (t.length > 3) builder.buildEnemy("escalationRisk", t[3]);
                }
                case "ENVIRONMENT_REPORT" -> {
                    if (t.length > 1) builder.buildEnvironment("weather", t[1]);
                    if (t.length > 2) builder.buildEnvironment("visibility", t[2]);
                    if (t.length > 3) builder.buildEnvironment("cursedEnergyDensity", t[3]);
                }
                case "TIMELINE_EVENT" -> {
                    if (t.length > 1) builder.buildTimeline("timestamp", t[1]);
                    if (t.length > 2) builder.buildTimeline("type", t[2]);
                    if (t.length > 3) builder.buildTimeline("description", t[3]);
                }
                case "MISSION_RESULT" -> {
                    if (t.length > 1) builder.buildBaseInfo("outcome", t[1]);
                    if (t.length > 2) builder.buildBaseInfo("damageCost", t[2]);
                }
                case "EXTRA_DATA" -> {
                    if (t.length > 1) builder.buildExtra("tags", t[1]);
                    if (t.length > 2) builder.buildExtra("artifacts", t[2]);
                    if (t.length > 3) builder.buildExtra("notes", t[3]);
                }
            }
        } 
        else {
            switch (event) {
                case "MISSION", "[MISSION]" -> builder.buildBaseInfo(key, value);
                case "CURSE", "[CURSE]" -> builder.buildCurse(key, value);
                case "SORCERER", "[SORCERER]" -> builder.buildSorcerer(key, value);
                case "TECHNIQUE", "[TECHNIQUE]" -> builder.buildTechnique(key, value);
                case "ECONOMIC", "[ECONOMIC]", "ECONOMIC_ASSESSMENT" -> builder.buildEconomic(key, value);
                case "CIVILIAN", "[CIVILIAN]", "CIVILIAN_IMPACT" -> builder.buildCivilian(key, value);
                case "ENEMY", "[ENEMY]", "ENEMY_ACTION" -> builder.buildEnemy(key, value);
                case "ENVIRONMENT", "[ENVIRONMENT]", "ENVIRONMENT_REPORT" -> builder.buildEnvironment(key, value);
                case "TIMELINE", "[TIMELINE]", "TIMELINE_EVENT" -> builder.buildTimeline(key, value);
                case "EXTRA", "[EXTRA]", "EXTRA_DATA" -> builder.buildExtra(key, value);
            }
        }
    }
}

package com.mycompany.jujutsukaisen;
    
public abstract class Builder {
    protected Mission mission;

    public void createNewMission() {
        mission = new Mission();
    }

    public Mission getResult() {
        return mission;
    }

    public abstract void buildBaseInfo(String key, String value);
    public abstract void buildCurse(String key, String value);
    public abstract void buildSorcerer(String key, String value);
    public abstract void buildTechnique(String key, String value);
    public abstract void buildEconomic(String key, String value);
    public abstract void buildCivilian(String key, String value);
    public abstract void buildEnemy(String key, String value);
    public abstract void buildEnvironment(String key, String value);
    public abstract void buildTimeline(String key, String value);
    public abstract void buildExtra(String key, String value);
}

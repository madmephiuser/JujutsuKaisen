
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.Mission;
import com.mycompany.jujutsukaisen.Builder;
import com.mycompany.jujutsukaisen.MissionDirector;
import java.io.BufferedReader;
import java.io.FileReader;

public abstract class AbstractMissionParser implements MissionParser {
    protected MissionDirector director;
    protected Builder builder;
    private MissionParser next;

    public void setDirector(MissionDirector director, Builder builder) {
        this.director = director;
        this.builder = builder;
    }

    public MissionParser setNext(MissionParser next) {
        this.next = next;
        return next;
    }

    @Override
    public Mission parse(String filePath) {
        if (doCheck(filePath)) {
            return doParse(filePath);
        } else if (next != null) {
            if (next instanceof AbstractMissionParser amp) {
                amp.setDirector(this.director, this.builder);
            }
            return next.parse(filePath);
        }
        return null;
    }
    
    protected abstract boolean doCheck(String filePath);
    protected abstract Mission doParse(String filePath);

    protected String getFirstLine(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        } catch (Exception e) {
            return "";
        }
    }
}


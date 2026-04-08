
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.Mission;
import java.io.BufferedReader;
import java.io.FileReader;

public abstract class AbstractMissionParser implements MissionParser {
    protected AbstractMissionParser next;
    
    public AbstractMissionParser setNext(AbstractMissionParser next) {
        this.next = next;
        return next;
    }

    @Override
    public Mission parse(String filePath) {
        if (doCheck(filePath)) {
            return doParse(filePath);
        } else if (next != null) {
            return next.parse(filePath);
        } else {
            System.err.println("Ошибка распознавания файла: " + filePath);
            return null;
        }
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.jujutsukaisen.Mission;
import java.io.File;

public class JsonParser extends AbstractMissionParser {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean doCheck(String filePath) {
        String line = getFirstLine(filePath);
        return line.startsWith("{") || line.startsWith("[");
    }

    @Override
    protected Mission doParse(String filePath) {
        try {
            return mapper.readValue(new File(filePath), Mission.class);
        } catch (Exception e) {
            System.err.println("Ошибка JSON: " + e.getMessage());
            return null;
        }
    }
}

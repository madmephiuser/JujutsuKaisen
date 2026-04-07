/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.jujutsukaisen.Mission;
import java.io.File;


public class XmlParser implements MissionParser {
    private final XmlMapper xmlMapper;

    public XmlParser() {
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Mission parse(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) return null;
            return xmlMapper.readValue(file, Mission.class);  
            
        } catch (Exception e) {
            System.out.println("Ошибка в структуре XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.jujutsukaisen.Mission;
import java.io.File;


public class XmlParser extends AbstractMissionParser {
    private final XmlMapper xmlMapper;

    public XmlParser() {
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    protected boolean doCheck(String filePath) {
        return getFirstLine(filePath).startsWith("<");
    }

    @Override
    protected Mission doParse(String filePath) {
        try {
            return xmlMapper.readValue(new File(filePath), Mission.class);
        } catch (Exception e) {
            System.err.println("Ошибка XML: " + e.getMessage());
            return null;
        }
    }
}

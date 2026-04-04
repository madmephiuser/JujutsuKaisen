
package com.mycompany.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.mycompany.jujutsukaisen.Mission;
import java.io.File;
import java.io.IOException;

public class YamlParser implements MissionParser{
    
    private final YAMLMapper mapper;

    public YamlParser() {
        this.mapper = new YAMLMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }
    @Override
    public Mission parse(String filePath) {
        try {
            File file = new File(filePath);
            
            if (!file.exists()) {
                throw new IOException("Файл не найден: " + filePath);
            }
            return mapper.readValue(file, Mission.class);
        } catch (Exception e) {
            System.err.println("Ошибка YAML: " + e.getMessage());
            return null;
        }
    }
}
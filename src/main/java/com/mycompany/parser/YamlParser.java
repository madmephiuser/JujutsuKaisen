
package com.mycompany.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.mycompany.jujutsukaisen.Mission;
import java.io.File;

public class YamlParser extends AbstractMissionParser{
    
    private final YAMLMapper mapper;

    public YamlParser() {
        this.mapper = new YAMLMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }
    @Override
    protected boolean doCheck(String filePath) {
        String line = getFirstLine(filePath);
        return (!line.startsWith("<") && !line.startsWith("{") && line.contains(":"));
    }

    @Override
    protected Mission doParse(String filePath) {
        try {
            return mapper.readValue(new File(filePath), Mission.class);
        } catch (Exception e) {
            System.err.println("Ошибка YAML: " + e.getMessage());
            return null;
        }
    }
}
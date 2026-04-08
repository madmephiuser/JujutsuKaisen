
package com.mycompany.parser;

public class FactoryParser {
    public static MissionParser getParser(String filePath) {
        AbstractMissionParser json = new JsonParser();
        AbstractMissionParser xml = new XmlParser();
        AbstractMissionParser yaml = new YamlParser();
        AbstractMissionParser txt = new TxtParser();
        AbstractMissionParser dif = new DifParser();

        json.setNext(xml).setNext(yaml).setNext(txt).setNext(dif);
        return json;
    }
}

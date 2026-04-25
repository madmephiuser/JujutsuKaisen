
package com.mycompany.parser;

import com.mycompany.jujutsukaisen.MissionBuilder;
import com.mycompany.jujutsukaisen.MissionDirector;
import java.util.List;

public class FactoryParser {
    public static MissionParser getParser() {

        MissionBuilder builder = new MissionBuilder();
        MissionDirector director = new MissionDirector(builder);

        YamlParser yaml = new YamlParser();
        TxtParser txt = new TxtParser();
        DifParser dif = new DifParser();
        JsonParser json = new JsonParser();
        XmlParser xml = new XmlParser();

        List.of(yaml, txt, dif, json, xml).forEach(p -> p.setDirector(director, builder));

        yaml.setNext(txt);
        txt.setNext(dif);
        dif.setNext(json);
        json.setNext(xml);

        return yaml; 
    }
}
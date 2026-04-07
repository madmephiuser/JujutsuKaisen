
package com.mycompany.parser;

public class FactoryParser {
  public static MissionParser getParser(String filePath) {
        String lowerPath = filePath.toLowerCase();
        
        if (lowerPath.endsWith(".json")) {
            return new JsonParser();
        } else if (lowerPath.endsWith(".xml")) {
            return new XmlParser();
        } else if (lowerPath.endsWith(".txt")) {
            return new TxtParser();
        } else if (lowerPath.endsWith(".yaml") || lowerPath.endsWith(".yml")) {
            return new YamlParser();
        }else {
            return new DifParser();
        }
    }
}

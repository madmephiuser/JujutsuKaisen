/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        } else {
            return null;
        }
    }  
}

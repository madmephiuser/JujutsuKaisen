/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.jujutsukaisen.Mission;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class XmlParser {
    private final XmlMapper xmlMapper = new XmlMapper();

    public Mission parse(String filePath) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("Ошибка файл не найден");
                return null;
            }
            String content = Files.readString(file.toPath()).trim();
            if (!content.startsWith("<")) {
                System.out.println("Ошибка неверный формат файла");
                return null;
            }
            return xmlMapper.readValue(file, Mission.class);  
            
        } catch (IOException e) {
            System.out.println("Ошибка в структуре XML " + e.getMessage());
            return null;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Mission;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class JsonParser {
    private final ObjectMapper mapper = new ObjectMapper();

    public Mission parse(String filePath) {
        try {
            File file = new File(filePath);
            String content = Files.readString(file.toPath()).trim();
            if (!file.exists()) {
                System.out.println("Ошибка: Файл по адресу " + filePath + " не найден");
                return null;
            }
            if (!content.startsWith("{")) {
                System.out.println("Ошибка: неверный формат файла");
                return null;
            }
            return mapper.readValue(file, Mission.class);
        } catch (IOException e) {
            System.out.println("Ошибка в структуре JSON: " + e.getMessage());
            return null;
        }
    }
}

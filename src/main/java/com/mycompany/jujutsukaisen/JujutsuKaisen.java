/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;

import static com.mycompany.jujutsukaisen.MissionRenderer.showMission;
import java.util.Scanner;
import com.mycompany.parser.JsonParser;

/**
 *
 * @author Professional
 */
public class JujutsuKaisen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonParser jsonParser = new JsonParser();

        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();
        Mission mission = jsonParser.parse(path);
        
        if (mission != null) {
            showMission(mission);
        }
    }
}

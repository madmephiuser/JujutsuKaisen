/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;

import com.mycompany.parser.*;
import java.util.Scanner;

public class JujutsuKaisen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу миссии: ");
        String path = scanner.nextLine();

        MissionParser parser = FactoryParser.getParser(path);

        if (parser == null) {
            System.out.println("Неподдерживаемый формат файла");
            return;
        }
        Mission mission = parser.parse(path);

        if (mission != null) {
            MissionRenderer.showMission(mission);
        } else {
            System.out.println("Не удалось обработать данные миссии");
        }
    }
}
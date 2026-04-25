/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;


public class ShortReportDecorator extends MissionDecorator {

    public ShortReportDecorator(MissionDisplay decoratedDisplay) {
        super(decoratedDisplay);
    }

    @Override
    public void display(Mission mission) {
        System.out.println("Краткий отчет о миссии");
        System.out.println("ID миссии: " + mission.getMissionId());
        System.out.println("Дата: " + mission.getDate()); 
        System.out.println("Локация: " + mission.getLocation()); 
        System.out.println("Результат: " + mission.getOutcome()); 
    }
}

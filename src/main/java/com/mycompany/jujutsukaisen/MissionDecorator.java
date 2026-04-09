/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jujutsukaisen;

public class MissionDecorator implements MissionDisplay{
    protected MissionDisplay decoratedDisplay;

    public MissionDecorator(MissionDisplay decoratedDisplay) {
        this.decoratedDisplay = decoratedDisplay;
    }

    @Override
    public void display(Mission mission) {
        decoratedDisplay.display(mission);
    }
}

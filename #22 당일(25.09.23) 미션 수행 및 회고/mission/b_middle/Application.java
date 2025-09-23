package com.ohgiraffers.z_activity.mission.b_middle;

public class Application {
    public static void main(String[] args) {
        Character character = new Character();
        Character warrior = new Warrior();
        Character wizard = new Wizard();

        character.attack();
        warrior.attack();
        wizard.attack();
    }
}

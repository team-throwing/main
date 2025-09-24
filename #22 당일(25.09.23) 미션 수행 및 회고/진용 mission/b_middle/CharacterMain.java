package com.ohgiraffers.oop.z_activity.mission.b_middle;

public class CharacterMain {

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        Warrior warrior = new Warrior();
        Character character = new Character();

        wizard.attack();
        warrior.attack();
        character.attack();

    }
}

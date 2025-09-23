package com.ohgiraffers.z_activity.mission.b_middle;

import com.ohgiraffers.z_activity.mission.b_middle.strings.CharacterMessages;

public class Warrior extends Character {

    public Warrior(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack() {
        System.out.print(CharacterMessages.WARRIOR_ATTACK_MSG);
    }
}

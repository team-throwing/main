package com.ohgiraffers.z_activity.mission.b_middle;

import com.ohgiraffers.z_activity.mission.b_middle.strings.CharacterMessages;

public class Wizard extends Character {

    public Wizard(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack() {
        System.out.print(CharacterMessages.WIZARD_ATTACK_MSG);
    }

    public void castSpell() {
        System.out.print(CharacterMessages.WIZARD_SPELL_MSG);
    }
}

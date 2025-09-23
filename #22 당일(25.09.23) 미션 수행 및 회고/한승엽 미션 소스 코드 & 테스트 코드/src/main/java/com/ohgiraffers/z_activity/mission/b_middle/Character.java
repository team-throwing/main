package com.ohgiraffers.z_activity.mission.b_middle;

import com.ohgiraffers.z_activity.mission.b_middle.strings.CharacterMessages;

public class Character {
    
    protected String name;
    protected int hp;

    public Character(String name, int hp) {

        // 파라미터 검증
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("이름은 null 이거나 blank 일 수 없습니다.");
        }
        if (hp <= 0) {
            throw new IllegalArgumentException("hp 는 0 이하일 수 없습니다.");
        }

        this.name = name;
        this.hp = hp;
    }

    public void attack() {
        System.out.print(CharacterMessages.DEFAULT_ATTACK_MSG);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}

package com.ohgiraffers.activity.mission.b_middle;

public class Sol {
    public static void main(String[] args) {
        Wizard gandalf = new Wizard("Gandalf",15);
        Warrior aragon = new Warrior("aragon",45);

        gandalf.attack();
        aragon.attack();
    }
}

class Character {
    String name;
    int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public void attack() {
        System.out.println("기본 공격!");
    }
}

class Warrior extends Character {

    Warrior(String name, int hp) { //부모클래스에서 정의한 생성자 알아서 상속 못하나?
        super(name, hp);
    }

    @Override
    public void attack() {
        System.out.println("강력한 칼로 공격!");
    }
}

class Wizard extends Character {
    Wizard(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack() {
        System.out.println("화려한 마법으로 공격!");
    }
}
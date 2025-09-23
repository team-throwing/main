package com.ohgiraffers.z_activity.mission.b_middle;

import com.ohgiraffers.z_activity.mission.b_middle.strings.CharacterMessages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CharacterWarriorWizardTest {

    /// 콘솔 출력 테스트 ///
    private static ByteArrayOutputStream outputStream;

    @BeforeEach
    void setOutStream() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStream() {
        System.setOut(System.out);
    }

    @Test
    public void character_create_success() {
        // given
        String name = "Smith";
        int hp = 10;

        // when
        Character character = new Character(name, hp);

        // then
        Assertions.assertEquals(name, character.getName());
        Assertions.assertEquals(hp, character.getHp());
    }

    @Test
    public void character_attack_success() {
        // given
        String name = "Smith";
        int hp = 10;
        Character character = new Character(name, hp);

        // when
        character.attack();

        // then
        Assertions.assertEquals(outputStream.toString(), CharacterMessages.DEFAULT_ATTACK_MSG);
    }

    @Test
    public void warrior_attack_success() {
        // given
        String name = "Smith";
        int hp = 10;
        Character character = new Warrior(name, hp);

        // when
        character.attack();

        // then
        Assertions.assertEquals(outputStream.toString(), CharacterMessages.WARRIOR_ATTACK_MSG);
    }

    @Test
    public void wizard_attack_success() {
        // given
        String name = "Smith";
        int hp = 10;
        Character character = new Wizard(name, hp);

        // when
        character.attack();

        // then
        Assertions.assertEquals(outputStream.toString(), CharacterMessages.WIZARD_ATTACK_MSG);
    }

    @Test
    public void wizard_castSpell_success() {
        // given
        String name = "Smith";
        int hp = 10;
        Character character = new Wizard(name, hp);

        // when
        ((Wizard) character).castSpell();

        // then
        Assertions.assertEquals(outputStream.toString(), CharacterMessages.WIZARD_SPELL_MSG);
    }
}
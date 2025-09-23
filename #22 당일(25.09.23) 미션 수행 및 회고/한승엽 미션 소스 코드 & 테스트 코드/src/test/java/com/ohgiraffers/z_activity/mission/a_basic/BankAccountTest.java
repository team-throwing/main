package com.ohgiraffers.z_activity.mission.a_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// ToDo: Jacoco - 테스트 커버리지 분석 도구 활용해보자

class BankAccountTest {

    @Test
    void bankAccount_create_success() {
        // given
        String accountNumber = "123123";
        long initBalance = 1000L;

        // when
        BankAccount account = new BankAccount(accountNumber, initBalance);

        // then
        Assertions.assertEquals(accountNumber, account.getAccountNumber());
        Assertions.assertEquals(initBalance, account.getBalance());
    }

    @Test
    void bankAccount_create_fail_whenAccountNumberIsNull() {
        // 생성자 파라미터 중 계좌 번호가 null 이면 생성 실패
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(null, 1L);
        });
    }

    @Test
    void bankAccount_create_fail_whenAccountIsEmpty() {
        // 생성자 파라미터 중 계좌 번호가 빈 문자열이면 생성 실패
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("", 1L);
        });
    }

    @Test
    void bankAccount_create_fail_whenBalanceIsNegative() {
        // 생성자 파라미터 중 잔고가 음수면 생성 실패
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("123123", -1L);
        });
    }

    @Test
    void deposit_success() {
        // given
        long initBalance = 1000L;
        BankAccount account = new BankAccount("123123", initBalance);

        // when
        long depositAmount = 100L;
        account.deposit(depositAmount);

        // then
        Assertions.assertEquals(initBalance + depositAmount, account.getBalance());
    }

    @Test
    void deposit_fail_whenDepositAmountIsNegative() {
        // 입금액이 음수이면 실패
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // given
            long initBalance = 1000L;
            BankAccount account = new BankAccount("123123", initBalance);

            // when
            account.deposit(-(initBalance + 1));
        });
    }

    @Test
    void deposit_fail_whenDepositPlusBalanceIsOverflowed() {
        // 입금액 + 잔고가 Long.MAX_VALUE 를 초과하면 실패
        Assertions.assertThrows(ArithmeticException.class, () -> {
            // given
            long initBalance = Long.MAX_VALUE;
            BankAccount account = new BankAccount("123123", initBalance);

            // when
            account.deposit(1L);
        });
    }

    @Test
    void withdraw_success() {
        // given
        long initBalance = 1000L;
        BankAccount account = new BankAccount("123123", initBalance);

        // when
        long withdrawAmount = 100L;
        account.withdraw(withdrawAmount);

        // then
        Assertions.assertEquals(initBalance - withdrawAmount, account.getBalance());
    }

    @Test
    void withdraw_success_whenWithdrawAmountIsBiggerThanBalance() {
        // given
        long initBalance = 1000L;
        BankAccount account = new BankAccount("123123", initBalance);

        // when
        long withdrawAmount = 100000L;
        account.withdraw(withdrawAmount);

        // then
        Assertions.assertEquals(initBalance, account.getBalance());
    }

    @Test
    void withdraw_fail_whenWithdrawAmountIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BankAccount account = new BankAccount("123123", 1L);
            account.withdraw(-1L);
        });
    }
}
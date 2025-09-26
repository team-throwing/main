package com.ohgiraffers.oop.z_activity.mission.a_basic;

import java.util.Scanner;

public class BeginnerMain {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("1", 1000);
        Scanner sc = new Scanner(System.in);

        long amount = 0;

        while (amount >= 0) {
            amount = sc.nextLong();
            bankAccount.withdraw(amount);
            bankAccount.getBalance();
        }

    }
}

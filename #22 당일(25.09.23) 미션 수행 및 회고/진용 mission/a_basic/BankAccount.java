package com.ohgiraffers.oop.z_activity.mission.a_basic;

public class BankAccount {
    private String accountNumber;
    private long balance;

    public BankAccount(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(long amount) {
        this.balance += amount;
    }

    public void withdraw(long amount) {
        if (this.balance <= amount) {
            System.out.println("잔액이 부족합니다.");
        } else {
            this.balance -= amount;
        }
    }

    public void getBalance() {
        System.out.println("잔액: " + this.balance);
    }
}

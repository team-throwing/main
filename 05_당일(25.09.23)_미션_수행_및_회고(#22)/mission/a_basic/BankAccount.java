package com.ohgiraffers.z_activity.mission.a_basic;

public class BankAccount {
    private String accountNumber;
    private Long balance;

    public BankAccount(String accountNumber, Long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(Long amount){
        balance = balance + amount;
    }

    public Long withdraw(Long amount){
        if (balance < amount){
            System.out.println("잔액이 부족합니다.");
            return null;
        }

        this.balance = balance - amount;
        return this.balance;
    }
}

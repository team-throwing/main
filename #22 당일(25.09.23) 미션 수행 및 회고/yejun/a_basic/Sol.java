package com.ohgiraffers.activity.mission.a_basic;

public class Sol {
    
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("12345678",10000);
        System.out.println("남은 재산 : " + myAccount.getBalance());
        myAccount.deposit(500);
        System.out.println("남은 재산 : " + myAccount.getBalance());
        System.out.println("출금액 : " + myAccount.withdraw(500));
        System.out.println("남은 재산 : " + myAccount.getBalance());
        //myAccount.balance -= 300; // private이라 실패함

    }
    
}

class BankAccount {
    private String accountNumber;
    private long balance;

    BankAccount(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }
    public long withdraw(long amount) {
        // 굳이 else if 를 사용하기 보다는 제거 -> 가독성 ^
        if (this.balance < amount) {
            System.out.println("잔액이 부족합니다. 출금실패");
            return amount
        } 
        this.balance -= amount;
        System.out.print("출금액 : "+ amount);
        return amount;
        
    }
    public long getBalance() {
        return this.balance;
    }
}

package com.ohgiraffers.z_activity.mission.a_basic;

public class BankAccount {

    private String accountNumber;   // 계좌 번호
    private long balance;           // 잔액

    public BankAccount(String accountNumber, long balance) {

        // 파라미터 검증
        if (accountNumber == null || accountNumber.length() == 0) {
            throw new IllegalArgumentException("accountNumber 는 null 이거나 blank 일 수 없습니다.");
        }

        if (balance < 0) {
            throw new IllegalArgumentException("초기 잔고는 음수일 수 없습니다.");
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * 잔고를 입금액만큼 더한다.
     * @param amount 입금액
     * @throws IllegalArgumentException 입금액이 음수인 경우
     */
    public void deposit(long amount) {
        // 파라미터 검증
        if (amount < 0) {
            throw new IllegalArgumentException("입금액은 음수일 수 없습니다.");
        }
        // 오버플로우가 일어나는 지 검증
        if (Long.MAX_VALUE - balance < amount) {
            throw new ArithmeticException("오버플로우로 인한 입금 실패.");
        }

        this.balance += amount;
    }

    /**
     * 잔고를 출금액 만큼 제한다. 만약 잔고가 부족한 경우 출금하지 않는다.
     * @param amount 출금액
     * @IllegalArgumentException 출금액이 음수인 경우
     */
    public void withdraw(long amount) {
        // 파라미터 검증
        if (amount < 0) {
            throw new IllegalArgumentException("출금액은 음수일 수 없습니다.");
        }

        // 잔고 검사
        if (balance < amount) {
            System.out.println("잔액이 부족합니다.");
            return;
        }

        balance -= amount;
    }

    /**
     * 계좌 번호 조회
     * @return 계좌 번호
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 잔액 조회
     * @return 잔액
     */
    public long getBalance() {
        return balance;
    }
}

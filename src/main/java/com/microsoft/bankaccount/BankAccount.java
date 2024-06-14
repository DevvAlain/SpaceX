/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.microsoft.bankaccount;

/**
 *
 * @author Admin
 */

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private double overdraftLimit;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance, double overdraftLimit) {
        this.balance = initialBalance;
        this.overdraftLimit = overdraftLimit;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account opened with balance: " + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= -overdraftLimit) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            return true;
        }
        transactionHistory.add("Failed withdrawal attempt: " + amount);
        return false;
    }

    public void addInterest(double interestRate) {
        if (interestRate > 0) {
            double interest = balance * (interestRate / 100);
            balance += interest;
            transactionHistory.add("Interest added: " + interest);
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
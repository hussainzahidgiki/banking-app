package uk.ac.cardiff.bankingapp.bankaccounts;

import uk.ac.cardiff.bankingapp.transactions.Transaction;

import java.util.List;

public class BankAccount {
    private String accountHolderName;
    private Integer accountBalance;
    private List<Transaction> transactions;

    public BankAccount(String accountHolderName, Integer accountBalance, List<Transaction> transactions) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
        this.transactions = transactions;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

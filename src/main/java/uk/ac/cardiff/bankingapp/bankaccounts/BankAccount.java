package uk.ac.cardiff.bankingapp.bankaccounts;

import uk.ac.cardiff.bankingapp.transactions.Transaction;

import java.util.List;

public class BankAccount {

    private Integer Id;
    private String accountHolderName;
    private Integer accountBalance;
    private List<Transaction> transactions;

    //Two constructors ?
    public BankAccount(Integer Id, String accountHolderName, Integer accountBalance) {
        this.Id=Id;
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
    }
    public BankAccount(Integer Id, String accountHolderName, Integer accountBalance, List<Transaction> transactions) {
        this.Id=Id;
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

    public Integer getId() {
        return Id;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setId(Integer id) {
        Id = id;
    }
}

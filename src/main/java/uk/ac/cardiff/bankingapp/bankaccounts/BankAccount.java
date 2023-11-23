package uk.ac.cardiff.bankingapp.bankaccounts;

import uk.ac.cardiff.bankingapp.transactions.Transaction;

import java.util.List;

public class BankAccount {

    private Integer Id;
    private String accountHolderName;
    private Float accountBalance; //Not expecting amount to be too large so opted for Float
    private List<Transaction> transactions;

    /*

     * The reason we have two constructors here is to allow us to initialize bank accounts with
     * and without transactions.
     */
    public BankAccount(Integer Id, String accountHolderName, Float accountBalance) {
        this.Id = Id;
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
    }

    public BankAccount(Integer Id, String accountHolderName, Float accountBalance, List<Transaction> transactions) {
        this.Id = Id;
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

    public Float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Float accountBalance) {
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

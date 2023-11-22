package uk.ac.cardiff.bankingapp.transactions;

import java.util.Date;

public class Transaction {
    private Integer transactionAmount;
    private Date transactionDate;

    private TransactionType transactionType;

    public Transaction(Integer transactionAmount, Date transactionDate, TransactionType transactionType) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionType= transactionType;
    }

    @Override
    public String toString() {
        return "Transaction Date:"+transactionDate.toString()+" Transaction Amount:"+ transactionAmount/100;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}

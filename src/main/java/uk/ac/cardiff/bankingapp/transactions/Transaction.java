package uk.ac.cardiff.bankingapp.transactions;

import java.util.Date;

public class Transaction {
    private Float transactionAmount; //Not expecting amount to be too large so opted for Float
    private Date transactionDate;

    private TransactionType transactionType;

    public Transaction(Float transactionAmount, Date transactionDate, TransactionType transactionType) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionType= transactionType;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Float transactionAmount) {
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

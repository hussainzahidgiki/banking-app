package uk.ac.cardiff.bankingapp.transactions;

/**

 * Money can either be deposited(DEBIT) or taken out(CREDIT) from the bank account,
 * So it made sense to use an ENUM here
 */
public enum TransactionType {
    DEBIT,
    CREDIT
}

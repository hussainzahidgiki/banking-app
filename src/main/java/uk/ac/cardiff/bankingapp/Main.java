package uk.ac.cardiff.bankingapp;

import uk.ac.cardiff.bankingapp.bankaccounts.BankAccount;
import uk.ac.cardiff.bankingapp.transactions.Transaction;
import uk.ac.cardiff.bankingapp.transactions.TransactionType;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    /**

     * Helper method that takes in a List of BankAccount Objects and outputs their details in a formatted table
     */
    static void displayBankAccounts(List<BankAccount> bankAccounts) {
        String leftAlignFormat = "| %-3d | %-23s | %.2f   |%n";
        System.out.format("+-----+-------------------------+-----------+%n");
        System.out.format("| Id  | Account Holder Name     | Balance($)|%n");
        System.out.format("+-------------------------------+-----------+%n");
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.format(leftAlignFormat, bankAccounts.get(i).getId(), bankAccounts.get(i).getAccountHolderName(), bankAccounts.get(i).getAccountBalance());
        }
        System.out.format("+-----+-------------------------+-----------+%n");
    }

    /**

     * Helper method that takes in a BankAccount Object and outputs its details in a formatted table
     */
    static void displayBankAccount(BankAccount bankAccount) {
        String leftAlignFormat = "| %-3d | %-23s | %.2f   |%n";
        System.out.format("+-----+-------------------------+-----------+%n");
        System.out.format("| Id  | Account Holder Name     | Balance($)|%n");
        System.out.format("+-------------------------------+-----------+%n");
        System.out.format(leftAlignFormat, bankAccount.getId(), bankAccount.getAccountHolderName(), bankAccount.getAccountBalance());
        System.out.format("+-----+-------------------------+-----------+%n");
    }

    /**

     * Helper method that takes in a BankAccount Object and outputs its transactions in a formatted table
     */
    static void displayTransactions(BankAccount bankAccount) {
        if (!(bankAccount.getTransactions() == null)) {
            String leftAlignFormat = "| %-6s | %-23s | %.2f   |%n";
            System.out.format("+--------+------------------------------+----------+%n");
            System.out.format("| Type   | Transaction Date             | Amount($)|%n");
            System.out.format("+---------------------------------------+----------+%n");
            for (int i = 0; i < bankAccount.getTransactions().size(); i++) {
                System.out.format(leftAlignFormat, bankAccount.getTransactions().get(i).getTransactionType().toString(), bankAccount.getTransactions().get(i).getTransactionDate(), bankAccount.getTransactions().get(i).getTransactionAmount());
            }
            System.out.format("+--------+------------------------------+----------+%n");
        }
        else{
            System.out.println("No Transactions found for this account");
        }
    }

    public static void main(String[] args) {

        Boolean exit = false; /* if true, exits application */


        /*

         * Initializing our application with two bank accounts and transactions respectively
         * I was tempted to use LinkedList for storage here because they have better performance when it comes to adding/removing elements.
         * However, this would have compromised my performance for reading data and since my number of reads is far more than the number of writes I opted to use ArrayList.
         */
        List<Transaction> initTransactions = new ArrayList(Arrays.asList(new Transaction(500f, new Date(), TransactionType.DEBIT), new Transaction(200f, new Date(), TransactionType.CREDIT)));
        List<BankAccount> bankAccounts = new ArrayList(Arrays.asList(new BankAccount(1, "Jason", 3000f, initTransactions), new BankAccount(2, "Alex", 2000f, initTransactions)));

        while (!exit) {

            System.out.println("***** BANKING APPLICATION MAIN MENU *****");
            System.out.println("* Enter 1 to list all bank accounts     *");
            System.out.println("* Enter 2 to view a bank account        *");
            System.out.println("* Enter 3 to create a new bank account  *");
            System.out.println("* Enter 4 to make a transaction         *");
            System.out.println("* Enter 5 to view transactions          *");
            System.out.println("* Enter 6 to remove a bank account      *");
            System.out.println("* Enter 7 to exit the application       *");
            System.out.println("*****************************************");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Input: ");
            String optionSelected = scanner.nextLine();


            switch (optionSelected) {

                //list all bank accounts
                case "1": {
                    displayBankAccounts(bankAccounts);
                    break;
                }

                //view a bank account
                case "2": {
                    System.out.print("Account Holder Name:");
                    String accountHolderName = scanner.nextLine();
                    BankAccount bankAccount = bankAccounts.stream().filter(item -> item.getAccountHolderName().toLowerCase().equals(accountHolderName.toLowerCase())).findAny().orElse(null);
                    if (bankAccount != null) {
                        displayBankAccount(bankAccount);
                        System.out.println("Transactions:");
                        displayTransactions(bankAccount);
                    } else {
                        System.out.println("No account found !");
                    }
                    break;
                }

                //create a new bank account
                case "3": {
                    System.out.println("Please enter your new bank account details");

                    System.out.print("Account Holder Name:");
                    String newAccountHolderName = scanner.nextLine();

                    System.out.print("Account Balance:");
                    Float newAccountBalance = scanner.nextFloat();

                    BankAccount newBankAccount = new BankAccount(bankAccounts.get(bankAccounts.size() - 1).getId() + 1, newAccountHolderName, newAccountBalance);
                    bankAccounts.add(newBankAccount);

                    System.out.print("Bank account created successfully!");
                    break;
                }

                //make a transaction
                case "4": {
                    //step 1: Provide the user with bank accounts with which they can make a payment
                    System.out.println("Please select account you want to make a payment with");
                    displayBankAccounts(bankAccounts);

                    //step 2: Record the id of the account they want to use
                    System.out.print("Account Id:");
                    Integer payerAccountId = scanner.nextInt();

                    //step 3: See whether we have this account, if not, take them back to the menu
                    BankAccount payerAccount = bankAccounts.stream().filter(item -> item.getId().equals(payerAccountId)).findAny().orElse(null);
                    if (payerAccount == null) {
                        System.out.println("Invalid Input, Please try again");
                        break;
                    }

                    //step 4: Provide the user with bank accounts sans the account selected in the above step.
                    System.out.println("Please select recipient account");
                    List<BankAccount> bankAccountsLessPayer = bankAccounts.stream().filter(item -> !item.getId().equals(payerAccountId)).collect(Collectors.toList());
                    displayBankAccounts(bankAccountsLessPayer);

                    //step 4: Record the id of the account they want to send money to.
                    System.out.print("Recipient Account Id:");
                    Integer recipientAccountId = scanner.nextInt();

                    //step 5: See whether we have this account, if not, take them back to the menu
                    BankAccount recipientAccount = bankAccounts.stream().filter(item -> item.getId().equals(recipientAccountId)).findAny().orElse(null);
                    if (recipientAccount == null) {
                        System.out.println("Invalid Input, Please try again");
                        break;
                    }

                    //step 6: Record the amount they want to send, if amount is greater than their balance, take them back to the menu.
                    System.out.print("Please enter amount you want to send: ");
                    Float transactionAmount = scanner.nextFloat();
                    if (transactionAmount > payerAccount.getAccountBalance()) {
                        System.out.println("Not Enough Balance to make this transaction");
                        break;
                    }

                    //step 7: Add this transaction as CREDIT (take out) to the Payer Account.
                    List<Transaction> currentPayerTransactions = new ArrayList<>(payerAccount.getTransactions());
                    currentPayerTransactions.add(new Transaction(transactionAmount, new Date(), TransactionType.CREDIT));
                    payerAccount.setTransactions(currentPayerTransactions);
                    payerAccount.setAccountBalance(payerAccount.getAccountBalance() - transactionAmount);

                    //step 8: Add this transaction as DEBIT (deposit) to the Recipient Account.
                    List<Transaction> currentRecipientTransactions = new ArrayList<>(recipientAccount.getTransactions());
                    currentRecipientTransactions.add(new Transaction(transactionAmount, new Date(), TransactionType.DEBIT));
                    recipientAccount.setTransactions(currentRecipientTransactions);
                    recipientAccount.setAccountBalance(recipientAccount.getAccountBalance() + transactionAmount);

                    System.out.println("Transaction successful!");

                    break;
                }

                //view transactions
                case "5": {
                    System.out.println("Please select which account you want to see transactions for");
                    displayBankAccounts(bankAccounts);
                    System.out.print("Account Id:");
                    Integer accountId = scanner.nextInt();
                    BankAccount filteredBankAccount = bankAccounts.stream().filter(item -> item.getId().equals(accountId)).findAny().orElse(null);
                    if (filteredBankAccount != null) {
                        displayTransactions(filteredBankAccount);
                    } else {
                        System.out.print("Invalid Input");
                    }
                    break;
                }

                //remove a bank account
                case "6": {
                    System.out.println("Please select which account you want to remove");
                    displayBankAccounts(bankAccounts);
                    System.out.print("Account Id:");
                    Integer accountId = scanner.nextInt();
                    bankAccounts.removeIf(item -> item.getId().equals(accountId));
                    System.out.println("Account Removed Successfully!");
                    break;

                }

                //exit from the application
                case "7": {
                    System.out.println("Exiting from the application");
                    exit = true;
                    break;
                }

                default:
                    System.out.println("Invalid Input, Please try again");

            }

        }

    }
}

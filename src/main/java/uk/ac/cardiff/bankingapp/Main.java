package uk.ac.cardiff.bankingapp;

import uk.ac.cardiff.bankingapp.bankaccounts.BankAccount;
import uk.ac.cardiff.bankingapp.transactions.Transaction;
import uk.ac.cardiff.bankingapp.transactions.TransactionType;

import java.util.*;

public class Main {

    static void displayBankAccounts(List<BankAccount> bankAccounts) {
        String leftAlignFormat = "| %-3d | %-23s | %-9d |%n";
        System.out.format("+-----+-------------------------+-----------+%n");
        System.out.format("| Id  | Account Holder Name     | Balance($)|%n");
        System.out.format("+-------------------------------+-----------+%n");
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.format(leftAlignFormat, bankAccounts.get(i).getId(), bankAccounts.get(i).getAccountHolderName(), bankAccounts.get(i).getAccountBalance());
        }
        System.out.format("+-----+-------------------------+-----------+%n");
    }

    static void displayBankAccount(BankAccount bankAccount) {
        String leftAlignFormat = "| %-3d | %-23s | %-9d |%n";
        System.out.format("+-----+-------------------------+-----------+%n");
        System.out.format("| Id  | Account Holder Name     | Balance($)|%n");
        System.out.format("+-------------------------------+-----------+%n");
        System.out.format(leftAlignFormat, bankAccount.getId(), bankAccount.getAccountHolderName(), bankAccount.getAccountBalance());
        System.out.format("+-----+-------------------------+-----------+%n");
    }

    static void displayTransactions(BankAccount bankAccount) {
        String leftAlignFormat = "| %-5s | %-23s | %-8d |%n";
        System.out.format("+--------+------------------------------+----------+%n");
        System.out.format("| Type   | Transaction Date             | Amount($)|%n");
        System.out.format("+---------------------------------------+----------+%n");
        for (int i = 0; i < bankAccount.getTransactions().size(); i++) {
            System.out.format(leftAlignFormat, bankAccount.getTransactions().get(i).getTransactionType().toString(), bankAccount.getTransactions().get(i).getTransactionDate(), bankAccount.getTransactions().get(i).getTransactionAmount());
        }
        System.out.format("+--------+------------------------------+----------+%n");
    }

    public static void main(String[] args) {

        Boolean exit = false;

        List<Transaction> initTransactions = new ArrayList(Arrays.asList(new Transaction(50000, new Date(), TransactionType.DEBIT), new Transaction(50000, new Date(), TransactionType.CREDIT)));
        List<BankAccount> bankAccounts = new ArrayList(Arrays.asList(new BankAccount(1, "Hussain", 3000, initTransactions), new BankAccount(2, "Hamza", 2000, initTransactions)));

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

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
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
                    BankAccount bankAccount = bankAccounts.stream().filter(item -> item.getAccountHolderName().equals(accountHolderName)).findAny().orElse(null);
                    if (bankAccount != null) {
                        displayBankAccount(bankAccount);
                    }
                    else {
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
                    Integer newAccountBalance = scanner.nextInt();

                    BankAccount newBankAccount = new BankAccount(bankAccounts.get(bankAccounts.size()-1).getId()+1, newAccountHolderName, newAccountBalance);
                    bankAccounts.add(newBankAccount);
                    System.out.print("Bank account created successfully!");
                    break;
                }

                //make a transaction
                case "4": {
                    System.out.println("Please select account you want to make a transaction with");
                    displayBankAccounts(bankAccounts);
                    System.out.print("Account Id:");
                    Integer payerAccountId = scanner.nextInt();
                    System.out.println("Please select recipient account");
                    displayBankAccounts(bankAccounts);
                    System.out.print("Recipient Account Id:");
                    Integer recipientAccountId = scanner.nextInt();
                    System.out.print("Please enter amount you want to send: ");
                    Integer transactionAmount = scanner.nextInt();

                    BankAccount recipientAccount = bankAccounts.stream().filter(item -> item.getId().equals(recipientAccountId)).findAny().orElse(null);
                    BankAccount payerAccount = bankAccounts.stream().filter(item -> item.getId().equals(payerAccountId)).findAny().orElse(null);

                    if (payerAccount != null && recipientAccount != null) {
                        List<Transaction> currentPayerTransactions = new ArrayList<>(payerAccount.getTransactions());
                        currentPayerTransactions.add(new Transaction(transactionAmount, new Date(), TransactionType.CREDIT));
                        payerAccount.setTransactions(currentPayerTransactions);
                        payerAccount.setAccountBalance(payerAccount.getAccountBalance() - transactionAmount);

                        List<Transaction> currentRecipientTransactions = new ArrayList<>(recipientAccount.getTransactions());
                        currentRecipientTransactions.add(new Transaction(transactionAmount, new Date(), TransactionType.DEBIT));
                        recipientAccount.setTransactions(currentRecipientTransactions);
                        recipientAccount.setAccountBalance(recipientAccount.getAccountBalance() + transactionAmount);

                        System.out.print("Transaction successful!");
                    }
                    break;
                }

                case "5": {
                    System.out.println("Please select which account you want to see transactions for");
                    displayBankAccounts(bankAccounts);
                    System.out.print("Account Id:");
                    Integer accountId = scanner.nextInt();
                    BankAccount filteredBankAccount = bankAccounts.stream().filter(item -> item.getId().equals(accountId)).findAny().orElse(null);
                    displayTransactions(filteredBankAccount);
                    break;
                }

                case "6":{
                    System.out.println("Please select which account you want to remove");
                    displayBankAccounts(bankAccounts);
                    System.out.print("Account Id:");
                    Integer accountId = scanner.nextInt();
                    bankAccounts.removeIf(item-> item.getId().equals(accountId));
                    System.out.println("Account Removed Successfully!");
                    break;

                }

                case "7": {
                    System.out.println("Exiting from menu");
                    exit = true;
                    break;
                }

                default:
                    System.out.println("Invalid Input, Please try again");

            }

        }

    }
}

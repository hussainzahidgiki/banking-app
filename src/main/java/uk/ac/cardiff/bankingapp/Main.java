package uk.ac.cardiff.bankingapp;

import uk.ac.cardiff.bankingapp.bankaccounts.BankAccount;
import uk.ac.cardiff.bankingapp.transactions.Transaction;
import uk.ac.cardiff.bankingapp.transactions.TransactionType;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static void getBankAccounts(List<BankAccount> bankAccounts) {
        String leftAlignFormat = "| %-3d | %-23s | %-9d |%n";
        System.out.format("+-----+-------------------------+-----------+%n");
        System.out.format("| Id  | Account Holder Name     | Balance($)|%n");
        System.out.format("+-------------------------------+-----------+%n");
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.format(leftAlignFormat, bankAccounts.get(i).getId(), bankAccounts.get(i).getAccountHolderName(), bankAccounts.get(i).getAccountBalance());
        }
        System.out.format("+-----+-------------------------+-----------+%n");
    }

    static void getTransactions(BankAccount filteredBankAccount) {
        String leftAlignFormat = "| %-5s | %-23s | %-8d |%n";
        System.out.format("+--------+------------------------------+----------+%n");
        System.out.format("| Type   | Transaction Date             | Amount($)|%n");
        System.out.format("+---------------------------------------+----------+%n");
        for (int i = 0; i < filteredBankAccount.getTransactions().size(); i++) {
            System.out.format(leftAlignFormat, filteredBankAccount.getTransactions().get(i).getTransactionType().toString(), filteredBankAccount.getTransactions().get(i).getTransactionDate(), filteredBankAccount.getTransactions().get(i).getTransactionAmount());
        }
        System.out.format("+--------+------------------------------+----------+%n");
    }

    public static void main(String[] args) {


        System.out.println("Welcome to Hussain's Bank");
        System.out.println("Enter 1 to list all bank accounts");
        System.out.println("Enter 2 to create a new bank account");
        System.out.println("Enter 3 to make a transaction");
        System.out.println("Enter 4 to view transactions");

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please enter the option from the menu above");
        String optionSelected = scanner.nextLine();


        List<Transaction> initTransactions = new ArrayList(Arrays.asList(new Transaction(50000, new Date(), TransactionType.CREDIT), new Transaction(50000, new Date(), TransactionType.CREDIT)));
        List<BankAccount> bankAccounts = new ArrayList(Arrays.asList(new BankAccount(1, "Hussain", 3000, initTransactions), new BankAccount(2, "Hamza", 2000, initTransactions)));


        switch (optionSelected) {
            case "1": {
                getBankAccounts(bankAccounts);
                break;
            }

            case "2": {
                System.out.println("Please enter your new bank account details");
                System.out.print("Account holder name:");
                String newAccountHolderName = scanner.nextLine();
                System.out.print("Account Balance:");
                Integer newAccountBalance = scanner.nextInt();
                BankAccount newBankAccount = new BankAccount(3, newAccountHolderName, newAccountBalance);
                bankAccounts.add(newBankAccount);
                getBankAccounts(bankAccounts);
                break;
            }

            case "3": {
                System.out.println("Please select account you want to make a transaction with");
                getBankAccounts(bankAccounts);
                System.out.print("Account Id:");
                Integer payerAccountId = scanner.nextInt();
                System.out.println("Please select recipient account");
                getBankAccounts(bankAccounts);
                System.out.print("Recipient Account Id:");
                Integer recipientAccountId = scanner.nextInt();
                System.out.print("Please enter amount you want to send: ");
                Integer transactionAmount = scanner.nextInt();

                BankAccount recipientAccount = bankAccounts.stream().filter(item -> item.getId() == recipientAccountId).findAny().orElse(null);
                BankAccount payerAccount = bankAccounts.stream().filter(item -> item.getId() == payerAccountId).findAny().orElse(null);

                if (payerAccount != null && recipientAccount != null) {
                    List<Transaction> currentPayerTransactions = new ArrayList<>(payerAccount.getTransactions());
                    currentPayerTransactions.add(new Transaction(transactionAmount, new Date(), TransactionType.CREDIT));
                    payerAccount.setTransactions(currentPayerTransactions);
                    payerAccount.setAccountBalance(payerAccount.getAccountBalance() - transactionAmount);

                    List<Transaction> currentRecipientTransactions = new ArrayList<>(recipientAccount.getTransactions());
                    currentRecipientTransactions.add(new Transaction(transactionAmount, new Date(), TransactionType.DEBIT));
                    recipientAccount.setTransactions(currentRecipientTransactions);
                    recipientAccount.setAccountBalance(recipientAccount.getAccountBalance() + transactionAmount);

                    getTransactions(payerAccount);
                }
                break;
            }

            case "4": {
                System.out.println("Please select which account you want to see transactions for");
                getBankAccounts(bankAccounts);
                System.out.print("Account Id:");
                Integer accountId = scanner.nextInt();
                BankAccount filteredBankAccount = bankAccounts.stream().filter(item -> item.getId() == accountId).findAny().orElse(null);
                getTransactions(filteredBankAccount);
                break;
            }

            case "5": {
                System.out.println("Exit from menu");
                break;
            }

        }

    }
}
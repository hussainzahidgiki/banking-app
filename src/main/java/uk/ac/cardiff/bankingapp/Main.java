package uk.ac.cardiff.bankingapp;

import uk.ac.cardiff.bankingapp.bankaccounts.BankAccount;
import uk.ac.cardiff.bankingapp.transactions.Transaction;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Hussain's Bank, Please select bank account");

        List<Transaction> initTransactions = new ArrayList(Arrays.asList(new Transaction(50000, new Date()), new Transaction(50000, new Date())));
        List<BankAccount> bankAccounts = new ArrayList(Arrays.asList(new BankAccount("Hussain", 3000, initTransactions), new BankAccount("Hamza", 2000, initTransactions)));
        String leftAlignFormat = "| %-23s | %-9d |%n";

        System.out.format("+-------------------------+-----------+%n");
        System.out.format("| Account Holder Name     | Balance($)|%n");
        System.out.format("+-------------------------+-----------+%n");
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.format(leftAlignFormat, bankAccounts.get(i).getAccountHolderName(), bankAccounts.get(i).getAccountBalance()/1000);
        }
        System.out.format("+-------------------------+-----------+%n");
        //System.out.println(myBankAccount.getTransactions());
/*        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);*/
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapplication1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author HP VICTUS
 */
public class Main {

    public static void main(String[] args) {
        int option = 0, number;
        String name;
        double balance, amount;
        Bank bank = new Bank();
        Account account;
        Scanner scan = new Scanner(System.in);
        while (option != 6) {
            System.out.println("Main Menu");
            System.out.println("1. Display All Account");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit Program");
            System.out.println("");
            System.out.print("Enter Your Option: ");
            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    bank.listAccounts();
                    break;

                case 2:
                    System.out.print("Enter Account Name: ");
                    name = scan.nextLine();
                    System.out.print("Enter innitial balance: ");
                    balance = scan.nextDouble();
                    number = generateAccountNumber();
                    account = new Account(number, name, balance);
                    System.out.println("Your AccountID: " + number);
                    bank.openAccount(account);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    number = scan.nextInt();
                    bank.closeAccount(number);
                    break;

                case 4:
                    System.out.println("Enter Id: ");
                    number = scan.nextInt();
                    System.out.println("Enter amount: ");
                    amount = scan.nextDouble();
                    bank.depositMoney(number, amount);
                    /*System.out.print("Enter Account Number: ");
                    number = scan.nextInt();
                    System.out.println("Your Deposit: " + balance);*/
                    break;


                case 5:
                    System.out.println("Enter Id: ");
                    number = scan.nextInt();
                    System.out.println("Enter amount: ");
                    amount = scan.nextDouble();
                    bank.depositMoney(number, amount);
                    /*System.out.print("Enter Account Number: ");
                    number = scan.nextInt();
                    System.out.println("Your Deposit: " + balance);
                    System.out.print("Enter withdraw: ");
                    withdraw = scan.nextDouble();
                    System.out.println("balance: " + (balance - withdraw));*/
                    break;
            }
        }
    }
    
    public static int generateAccountNumber() {
        Random rand = new Random();
        int number = 100000 + rand.nextInt(900000);
        return number;
    }
}

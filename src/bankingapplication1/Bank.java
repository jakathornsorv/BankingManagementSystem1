/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapplication1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP VICTUS
 */
public class Bank {

    private String name;

    public Bank() {

    }

    public Bank(String name) {
        this.name = name;
    }

    public void openAccount(Account account) {
        Connection connection = Bankingconnection.connect();
        String sql = "INSERT INTO account (accNumber, accName, accBalance) "
                + "VALUES(?, ?, ?) ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeAccount(int number) {
        Connection connection = Bankingconnection.connect();
        String sql = "DELETE FROM account WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void depositMoney(int number, double amount) {
        Account account = getAccount(number);
        account.deposit(amount);
        Connection connection = Bankingconnection.connect();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            System.out.println("Balance: " + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void withdrawMoney(int number, double amount) {
        Account account = getAccount(number);
        account.withdraw(amount);
        Connection connection = Bankingconnection.connect();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
            System.out.println("Balance: " + account.getBalance());
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listAccounts() {
        Connection connection = Bankingconnection.connect();
        Statement statement;

        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                System.out.println(results.getString(1) + " " + results.getString(2) + " "
                        + results.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int number) {
        Connection connection = Bankingconnection.connect();
        String sql = "SELECT * FROM account WHERE accNumber=?";
        PreparedStatement preparedStatement;
        Account account = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet result = preparedStatement.executeQuery();
            
            result.next();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            account = new Account(number, accName, balance);
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }

}

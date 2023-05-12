/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapplication1;

import java.sql.*;

/**
 *
 * @author HP VICTUS
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bankingconnection {
    
    public static Connection connect() {
        String URL = "jdbc:mysql://localhost:3306/mydb1";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bankingconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Bankingconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}

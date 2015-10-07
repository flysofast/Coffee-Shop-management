/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aptech
 */
public class DatabaseConnector {

    static Connection con = null;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=BTL";

    DatabaseConnector() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                con = DriverManager.getConnection(url, "sa", "123456");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ham singleton - tuong tu ham getInstance cua may lop DAO.
     *
     * @return doi tuong connection
     */
    public static Connection getConnection() {
        if (con == null) {
            new DatabaseConnector();
        }
        return con;
    }

}

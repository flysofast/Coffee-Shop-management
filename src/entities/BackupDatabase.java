/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.DatabaseConnector;

/**
 *
 * @author Ashley
 */
public class BackupDatabase {
    
    public static boolean BackupDatabase(String path){
     
        try {
            PreparedStatement pr =DatabaseConnector.getConnection().prepareStatement("BACKUP DATABASE BTL TO DISK = '"+path+"' ");
            pr.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BackupDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }
}

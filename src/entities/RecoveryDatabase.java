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
public class RecoveryDatabase {
    public static boolean RecoveryDatabase(String pathIn) {
     
        try {
            String sql = "";
            String db_name="BTL";
            String path=pathIn;
            sql = "alter database " + db_name + " set offline with rollback immediate;";
            sql += "restore database " + db_name + " from disk = '" + path+"'";
            sql += " with replace ";
            sql += "alter database " + db_name + " set onLine with rollback immediate;";
            System.out.println("sql query: "+sql);
            PreparedStatement pr = DatabaseConnector.getConnection().prepareStatement(sql);
            pr.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RecoveryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }
}

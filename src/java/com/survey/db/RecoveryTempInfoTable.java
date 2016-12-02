/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KD
 */
public class RecoveryTempInfoTable {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public String InsertRecoveryTemp(String email_address, String auto_id) {
        String message = "";
        String sql = "INSERT INTO recovery_temp_info(email_address, auto_id) VALUES (?,?)";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, email_address);
            pst.setString(2, auto_id);
            
            if (pst.execute()) {
                message = "";
            }
            else{
                message = "Error: unable to insert data into database!";
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }

    public boolean checkRecoveryTable(String email, String id) {
        boolean flag = false;
        
        con = MySQLConnection.connect();
        String sql = "SELECT serial_id FROM recovery_temp_info WHERE email_address=? AND auto_id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, id);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }

    public void deleteRaws(String email_address) {
        con = MySQLConnection.connect();
        String sql = "DELETE FROM recovery_temp_info WHERE email_address=?";
      try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email_address);
            pst.executeUpdate();
            
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

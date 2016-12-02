/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KD
 */
public class UserTable {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public boolean login(User user, String sql){
        con = MySQLConnection.connect();
        
        boolean bool = false;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            
            rs = pst.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    bool = true;
                }
                else {
                    bool = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
}

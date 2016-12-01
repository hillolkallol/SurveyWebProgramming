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
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public User userInfo(String username){
        User userDetails = new User();
        
        String sql = "SELECT * FROM users WHERE username=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                String user_id = rs.getString("user_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email_address = rs.getString("email_address");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                String date_of_birth = rs.getString("date_of_birth");
                String state_id = rs.getString("state_id");
                
                userDetails.setUsername(username);
                userDetails.setPassword(password);
                userDetails.setUser_id(user_id);
                userDetails.setFirst_name(first_name);
                userDetails.setLast_name(last_name);
                userDetails.setEmail_address(email_address);
                userDetails.setGender(gender);
                userDetails.setDate_of_birth(date_of_birth);
                userDetails.setState_id(state_id);
                
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userDetails;
    }

    public String updateUserInfo(User userDetails, String sql) {
        String message = "";
        
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, userDetails.getFirst_name());
            pst.setString(2, userDetails.getLast_name());
            pst.setString(3, userDetails.getEmail_address());
            pst.setString(4, userDetails.getPassword());
            pst.setString(5, userDetails.getUsername());
            
            if (pst.executeUpdate()!= 0) {
                message = "New Information has been updated!";
            }
            else{
                message = "There was some problem in updating!" + userDetails.getUsername();
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
}

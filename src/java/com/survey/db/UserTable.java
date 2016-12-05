/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.models.User;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author KD
 */
public class UserTable {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public boolean login(User user){
        con = MySQLConnection.connect();
        String sql = "SELECT username, password FROM users WHERE username=? AND password=?";
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

    public String updateUserInfo(User userDetails) {
        String message = "";
        String sql = "UPDATE users SET first_name=?,last_name=?,email_address=?,password=? WHERE username=?";
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
                message = "There was some problem in updating!";
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }

    public String passRecovery(String email_or_username, HttpServletRequest request) throws AddressException, MessagingException, MalformedURLException {
        String msg = "";
        
        String sql = "SELECT email_address FROM users WHERE username=? OR email_address=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email_or_username);
            pst.setString(2, email_or_username);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                String email_address = rs.getString("email_address");
                if(email_address != null){
                    String to = email_address;
                    String from = "care@survey.com";
                    String host = "localhost";
                    String subject = "Passowrd Recovery | Survey";
                    
                    SecureRandom random = new SecureRandom();
                    String auto_id = new BigInteger(130, random).toString(32);
                    URL url = new URL(request.getRequestURL().toString());
                    String link = "http://" + url.getAuthority() + "/SurveyWebProgramming/recovery?email="+email_address+"&id="+auto_id+"";
                    
                    String body = "Click in this <a href='" + link + "'>link</a> to create a new password!";
                    
                    Properties properties = System.getProperties();
                    properties.setProperty("mail.smtp.host", host);
                    Session mailSession = Session.getDefaultInstance(properties);
                    msg = "A link has been sent to your email! Check this out! </br>" + body;

                    try{
                       MimeMessage message = new MimeMessage(mailSession);
                       message.setFrom(new InternetAddress(from));
                       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                       message.setSubject(subject);
                       message.setText(body);
                       RecoveryTempInfoTable recoveryTempObj = new RecoveryTempInfoTable();
                       recoveryTempObj.InsertRecoveryTemp(email_address, auto_id);
                       Transport.send(message);
                       msg = "A link has been sent to your email! Check this out!\n" + body;
                    }catch (MessagingException mex) {
                       //msg = "Error: unable to send message....";
                    }
                }
            }
            else{
                msg = "Username/Email not found!";
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msg;
    }
    
    public String resetPassword(User userDetails) {
        String message = "";
        String sql = "UPDATE users SET password=? WHERE email_address=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, userDetails.getPassword());
            pst.setString(2, userDetails.getEmail_address());
            //JOptionPane.showMessageDialog(null, userDetails.getPassword() + " " + userDetails.getEmail_address());
            
            if (pst.executeUpdate()!= 0) {
                message = "New Information has been updated!";
                RecoveryTempInfoTable recoveryTempInfoTable = new RecoveryTempInfoTable();
                recoveryTempInfoTable.deleteRaws(userDetails.getEmail_address());
            }
            else{
                message = "There was some problem in updating!";
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }

    public long findUserID(String username) {
        long user_id = 0;
        con = MySQLConnection.connect();
        String sql = "SELECT user_id FROM users WHERE username=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                user_id = rs.getLong("user_id");
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_id;
    }
}

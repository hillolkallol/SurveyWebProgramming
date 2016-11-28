/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

/**
 *
 * @author KD
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MySQLConnection {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/surveydb", "root", "");//connection established!!!
            //JOptionPane.showMessageDialog(null, "Connection Established!");
            return con;
        } catch (Exception e) {
            return null;
        }
    }
//    public static void main(String args[]){
//        MySQLConnection con = new MySQLConnection();
//        con.connect();
//    }
}

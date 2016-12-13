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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/surveydb", "root", "");
//            Connection con = DrsiverManager.getConnection("jdbc:mysql://localhost:3306/apollo10_survey_system", "apollo10", "udGo1Qrt3L");//connection established!!!
            //JOptionPane.showMessageDialog(null, "Connection Established!");
            return con;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Sorry!");
            return null;
        }
    }
//    public static void main(String args[]){
//        MySQLConnection con = new MySQLConnection();
//        con.connect();
//    }
}

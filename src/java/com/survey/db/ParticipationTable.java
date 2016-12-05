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
public class ParticipationTable {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    public int totalParticipent(long surveyID) {
        int totalParticipent = 0;
        con = MySQLConnection.connect();
        String sql = "SELECT user_id FROM participation WHERE survey_id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setLong(1, surveyID);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                totalParticipent++;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return totalParticipent;
    }
    
}

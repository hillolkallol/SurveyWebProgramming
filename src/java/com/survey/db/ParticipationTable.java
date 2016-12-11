/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.controllers.DashboardController;
import com.survey.models.BeanSurveyModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    /**
     *
     * @param beanSurveyModuleModel
     * @return
     */
    public List<Integer> totalParticipent(List <BeanSurveyModule> beanSurveyModuleModel) {
        List<Integer> list = new ArrayList<>();
        int totalParticipent = 0;
        con = MySQLConnection.connect();
        String sql = "SELECT user_id FROM participation WHERE survey_id=?";
        try {
            pst = con.prepareStatement(sql);
            for(int i=0; i<beanSurveyModuleModel.size(); i++){
                pst.setLong(1, beanSurveyModuleModel.get(i).getSurveyID());
            
                rs = pst.executeQuery();
                totalParticipent = 0;
                while (rs.next()) {
                    totalParticipent++;
                }
                list.add(totalParticipent);
            }
            close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public List<Integer> allSurveyID(long findUserID) {
        List<Integer> list = new ArrayList<>();
        con = MySQLConnection.connect();
        String sql = "SELECT survey_id FROM participation WHERE user_id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setLong(1, findUserID);
            rs = pst.executeQuery();
            while (rs.next()) {
                int survey_id = rs.getInt("survey_id");
                list.add(survey_id);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public void insertIntoParrticipantionTable(long survey_id, int user_id) throws SQLException{
        String insertStatement = "INSERT INTO participation (survey_id, user_id, survey_time) VALUES (?,?,?);";
        try {
            DashboardController dashboardController = new DashboardController();
            con = MySQLConnection.connect();
            pst = con.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            pst.setLong(1, survey_id);
            pst.setInt(2, user_id);
            pst.setTimestamp(3, dashboardController.getCurrentTimeStamp());

            int affectedRows = pst.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Survey failed, no rows affected!");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close();
        }
    }
    
    private void close() {
        try {
            if (rs != null) {
                rs.close();
            }

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

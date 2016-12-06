/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.models.BeanSurveyModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                while (rs.next()) {
                    totalParticipent++;
                }
                list.add(totalParticipent);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}

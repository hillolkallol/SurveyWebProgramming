/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.models.BeanSurveyModule;
import com.survey.models.BeanSurveyModuleList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ratul
 */
public class SurveyTable {

    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    //INSERT INTO survey_details (survey_tile, survey_description) VALUES ("temporary title", "this can be description");
    private String insertStatement = "INSERT INTO survey_details (survey_tile, survey_description) VALUES (?,?);";

    public void insertIntoSurveyTable(BeanSurveyModule beanSurveyModule) throws SQLException {
        {
            try {
                connection = MySQLConnection.connect();
                preparedStatement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, beanSurveyModule.getSurveyTitle());
                preparedStatement.setString(1, beanSurveyModule.getSurveyDesc());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating Survey failed, no rows affected!");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        beanSurveyModule.setSurveyID(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

            } catch (SQLException ex) {
                throw ex;
            }
        }
    }

    public List <BeanSurveyModule> selectFromSurveyTable(String username) {
        //find user id
        UserTable userTable = new UserTable();
        long user_id = userTable.findUserID(username);
        
        connection = MySQLConnection.connect();
        String sql = "SELECT survey_id, survey_title, last_modification_time, publish_time FROM survey_details WHERE user_id=?";
        
        BeanSurveyModule beanSurveyModule = new BeanSurveyModule();
        List <BeanSurveyModule> beanSurveyModulesList = new ArrayList<>();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user_id);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long survey_id = resultSet.getLong("survey_id");
                String survey_title = resultSet.getString("survey_title");
                Timestamp last_modification_time = resultSet.getTimestamp("last_modification_time");
                Timestamp publish_time = resultSet.getTimestamp("publish_time");
                
                beanSurveyModule.setSurveyID(survey_id);
                beanSurveyModule.setSurveyTitle(survey_title);
                beanSurveyModule.setModificationTime(last_modification_time);
                beanSurveyModule.setPublishTime(publish_time);
                beanSurveyModule.setUserID(user_id);
                
                beanSurveyModulesList.add(beanSurveyModule);
            }
            
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beanSurveyModulesList;
    }
    
    
}

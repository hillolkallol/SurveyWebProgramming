/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.models.BeanOptionModule;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
import com.survey.models.ProjectVariable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ratul
 */
public class OptionsTable {
    
    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    
    private static final String COL_OPTION_ID = "option_id";
    private static final String COL_OPTION_DESC = "option_desc";
    private static final String COL_QUESTION_ID = "question_id";
    private static final String COL_OPTION_ORDER = "option_order";

    //INSERT INTO options (option_desc, question_id) VALUES ("this is the first option", "1");
    //private final String insertStatement = "INSERT INTO options (option_desc, question_id) VALUES (?,?);";
    private final String insertStatement = "INSERT INTO options (" + COL_OPTION_DESC + ", " + COL_QUESTION_ID + ") VALUES (?,?);";
    private final String selectByQuestionIdStatement = "SELECT * FROM options WHERE " + COL_QUESTION_ID + " = ?;";
    
    public void insertIntoOptionTableQnModule(BeanSurveyModule beanSurveyModule) throws SQLException {
        {
            try {
                connection = MySQLConnection.connect();
                preparedStatement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
                int questionModuleNo = 0;
                for (BeanQuestionModule questionModule : beanSurveyModule.getQuestionModules()) {
                    for (int cntOption = 0; cntOption < ProjectVariable.NO_OF_MAX_OPTION; cntOption++) {
                        preparedStatement.setString(1, questionModule.getOptions().get(cntOption).getOptionDesc());
                        preparedStatement.setString(2, String.valueOf(questionModule.getQuestionId()));
                        
                        int affectedRows = preparedStatement.executeUpdate();
                        
                        if (affectedRows == 0) {
                            throw new SQLException("Creating Option failed, no rows affected!");
                        }
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                questionModule.getOptions().get(cntOption).setOptionId(generatedKeys.getLong(1));
                            } else {
                                throw new SQLException("Creating Option failed, no ID obtained.");
                            }
                        }
                    }
                }
                
            } catch (SQLException ex) {
                throw ex;
            } finally {
                close();
            }
        }
    }
    
    public BeanQuestionModule selectAllOptionsByQuestion(BeanQuestionModule question) throws SQLException {
        //BeanQuestionModule questionModule = new BeanQuestionModule();
        List<BeanOptionModule> options = new ArrayList<>();
        try {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(selectByQuestionIdStatement);
            preparedStatement.setDouble(1, question.getQuestionId());
            resultSet = preparedStatement.executeQuery();
            //resultSet.get

            while (resultSet.next()) {
                BeanOptionModule optionModule = new BeanOptionModule();
                optionModule.setOptionId(resultSet.getLong(COL_OPTION_ID));
                optionModule.setOptionDesc(resultSet.getString(COL_OPTION_DESC));
                optionModule.setQuestionId(question.getQuestionId());
                optionModule.setOrder(resultSet.getInt(COL_OPTION_ORDER));
                //questionModule.setOptions(optionModule);
                //questionModule.addOption(optionModule);
                //question.addOption(optionModule);
                options.add(optionModule);
            }
            question.setOptions(options);
            return question;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close();
        }
        
    }
    
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.controllers.RunningSurvey;
import com.survey.models.BeanOptionModule;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
import com.survey.models.BeanUserSelectionOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ratul
 */
public class UserSelectionOptionTable {

    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    private static final String COL_SELECTION_ID = "selection_id";
    private static final String COL_SURVEY_ID = "survey_id";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_QUESTION_ID = "question_id";
    private static final String COL_OPTION_ID = "option_id";

    //INSERT INTO options (option_desc, question_id) VALUES ("this is the first option", "1");
    private final String insertStatement = "INSERT INTO user_selected_option (" + COL_SURVEY_ID + "," + COL_USER_ID + "," + COL_QUESTION_ID + "," + COL_OPTION_ID + ") VALUES (?,?,?,?);";
    //SELECT COUNT(DISTINCT user_id) FROM firedata_2016.user_selected_option WHERE survey_id = 13;
    private final String selectDistinctUserBySurvey = "SELECT COUNT(DISTINCT user_id) FROM user_selected_option WHERE " + COL_SURVEY_ID + "=?;";

    private final String selectDistinctUserByOption = "SELECT COUNT(DISTINCT user_id) FROM user_selected_option WHERE " + COL_OPTION_ID + "=?;";

    public void insertIntoUserSelectionTableByList(List<BeanUserSelectionOption> allBeanUserSelectionOption) throws SQLException {
        try {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

            for (BeanUserSelectionOption singleOption : allBeanUserSelectionOption) {
                preparedStatement.setLong(1, singleOption.getSurveyId());
                preparedStatement.setLong(2, singleOption.getUserId());
                preparedStatement.setLong(3, singleOption.getQuestionId());
                preparedStatement.setLong(4, singleOption.getOptionId());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating Option failed, no rows affected!");
                }
            }

        } catch (SQLException ex) {
            throw ex;
        } finally {
            close();
        }
    }

    public int selectNumberOfUserOfSurveyId(long surveyId) throws SQLException
    {
        int numberOfDistinctUser;
        try
        {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(selectDistinctUserBySurvey);
            preparedStatement.setDouble(1, surveyId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfDistinctUser = Integer.parseInt(resultSet.getString(1));
                return numberOfDistinctUser;
            }
            return 0;
        }catch (SQLException | NumberFormatException ex) {
            throw ex;
        } finally {
            close();
        }
    }
    
    public int selectNumberOfUserByOptionId(long optionId) throws SQLException {
        int numberOfDistinctUser;
        try {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(selectDistinctUserByOption);
            preparedStatement.setDouble(1, optionId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfDistinctUser = Integer.parseInt(resultSet.getString(1));
                return numberOfDistinctUser;
            }
            return 0;
        } catch (SQLException | NumberFormatException ex) {
            throw ex;
        } finally {
            close();
        }
    }
	
	public void selectNumberOfUserBySurvey(BeanSurveyModule surveyModule) {
        for (int i = 0; i < surveyModule.getQuestionModules().size(); i++) {

            for (int j = 0; j < surveyModule.getQuestionModules().get(i).getOptions().size(); j++) {
                try {
                    int val = selectNumberOfUserByOptionId(surveyModule.getQuestionModules().get(i).getOptions().get(j).getOptionId());
                    surveyModule.getQuestionModules().get(i).getOptions().get(j).setNoOfUserSelected(val);
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(RunningSurvey.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
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

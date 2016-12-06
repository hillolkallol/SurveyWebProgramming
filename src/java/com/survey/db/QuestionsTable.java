/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
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
public class QuestionsTable {

    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private static final String COL_QUESTION_ID = "question_id";
    private static final String COL_QUESTION_DESC = "question";
    private static final String COL_SURVEY_ID = "survey_id";

    //INSERT INTO questions (question, survey_id) VALUES ("this is the first question", "1");
    private final String insertStatement = "INSERT INTO questions (question, survey_id) VALUES (?,?);";
    private final String selectByIdStatement = "SELECT * FROM questions WHERE survey_id = ?;";

    public void insertIntoQuestionTable(BeanSurveyModule beanSurveyModule) throws SQLException {
        {
            try {
                connection = MySQLConnection.connect();
                preparedStatement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
                int questionModuleNo = 0;
                for (BeanQuestionModule questionModule : beanSurveyModule.getQuestionModules()) {
                    preparedStatement.setString(1, questionModule.getQuestionDesc());
                    preparedStatement.setString(2, String.valueOf(beanSurveyModule.getSurveyID()));
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Creating Question failed, no rows affected!");
                    }
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            beanSurveyModule.getQuestionModules().get(questionModuleNo).setQuestionId(generatedKeys.getLong(1));
                            beanSurveyModule.getQuestionModules().get(questionModuleNo).setSurveyId(beanSurveyModule.getSurveyID());
                            questionModuleNo++;
                            //beanSurveyModule.setSurveyID(generatedKeys.getLong(1));
                        } else {
                            throw new SQLException("Creating user failed, no ID obtained.");
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

    public List<BeanQuestionModule> selectAllQuestionsById(long surveyId) throws SQLException {
        List<BeanQuestionModule> allQuestionModule = new ArrayList<>();
        try {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(selectByIdStatement);
            preparedStatement.setDouble(1, surveyId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BeanQuestionModule singleQestion = new BeanQuestionModule();
                singleQestion.setQuestionId(resultSet.getLong(COL_QUESTION_ID));
                singleQestion.setQuestionDesc(resultSet.getString(COL_QUESTION_DESC));
                singleQestion.setSurveyId(surveyId);
                allQuestionModule.add(singleQestion);
            }
            return allQuestionModule;
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

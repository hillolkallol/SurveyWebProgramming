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
import java.sql.Statement;

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
}

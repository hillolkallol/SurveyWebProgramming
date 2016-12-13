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

    private static final String colSurveyTitle = "survey_title";
    private static final String colSurveyDesc = "survey_description";
    private static final String colSurvyLogoLoc = "logo_location";
    private static final String colSurveyLastModifTime = "last_modification_time";
    private static final String colSurveyPublishedTime = "publish_time";
    private static final String colSurveyClosingTime = "closing_time";

    //INSERT INTO survey_details (survey_title, survey_description) VALUES ("temporary title", "this can be description");
    private final String insertStatement = "INSERT INTO survey_details (survey_title, survey_description, last_modification_time, publish_time, user_id) VALUES (?,?,?,?,?);";
    private final String selectByIdStatement = "SELECT * FROM survey_details WHERE survey_id = ?;";

    public void insertIntoSurveyTable(BeanSurveyModule beanSurveyModule) throws SQLException {

        try {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, beanSurveyModule.getSurveyTitle());
            preparedStatement.setString(2, beanSurveyModule.getSurveyDesc());
            preparedStatement.setTimestamp(3, beanSurveyModule.getLastModifiedTime());
            preparedStatement.setTimestamp(4, beanSurveyModule.getPublishedTime());
            preparedStatement.setLong(5, beanSurveyModule.getUserID());

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
        } finally {
            close();
        }

    }

    public BeanSurveyModule selectSurveyById(long surveyId) throws SQLException {
        BeanSurveyModule beanSurveyModule = new BeanSurveyModule();
        try {
            connection = MySQLConnection.connect();
            preparedStatement = connection.prepareStatement(selectByIdStatement);
            preparedStatement.setDouble(1, surveyId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                beanSurveyModule.setSurveyID(surveyId);
                beanSurveyModule.setSurveyTitle(resultSet.getString(colSurveyTitle));
                beanSurveyModule.setSurveyDesc(resultSet.getString(colSurveyDesc));
                beanSurveyModule.setLogoLocation(resultSet.getString(colSurvyLogoLoc));
                beanSurveyModule.setLastModifiedTime(resultSet.getTimestamp(colSurveyLastModifTime));
                beanSurveyModule.setPublishedTime(resultSet.getTimestamp(colSurveyPublishedTime));
                beanSurveyModule.setClosingTime(resultSet.getTimestamp(colSurveyClosingTime));
            }
            return beanSurveyModule;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            close();
        }
    }

    public List<BeanSurveyModule> selectFromSurveyTable(String username) {
        //find user id
        UserTable userTable = new UserTable();
        long user_id = userTable.findUserID(username);

        connection = MySQLConnection.connect();
        String sql = "SELECT survey_id, survey_title, last_modification_time, publish_time FROM survey_details WHERE user_id=?";

        List<BeanSurveyModule> beanSurveyModulesList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user_id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BeanSurveyModule beanSurveyModule = new BeanSurveyModule();

                Long survey_id = resultSet.getLong("survey_id");
                String survey_title = resultSet.getString("survey_title");
                Timestamp last_modification_time = resultSet.getTimestamp("last_modification_time");
                Timestamp publish_time = resultSet.getTimestamp("publish_time");

                beanSurveyModule.setSurveyID(survey_id);
                beanSurveyModule.setSurveyTitle(survey_title);
                beanSurveyModule.setLastModifiedTime(last_modification_time);
                beanSurveyModule.setPublishedTime(publish_time);
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
    
    public List<BeanSurveyModule> surveyDetailsParticipatedByMe(List <Integer> survey_id) {
        //find user id
        UserTable userTable = new UserTable();

        List<BeanSurveyModule> beanSurveyModulesList = new ArrayList<>();
        for(int i = 0; i < survey_id.size(); i++){
            connection = MySQLConnection.connect();
            String sql = "SELECT survey_id, survey_title, survey_description, last_modification_time FROM survey_details WHERE survey_id = ?";
            
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, survey_id.get(i));

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    BeanSurveyModule beanSurveyModule2 = new BeanSurveyModule();

                    int id = resultSet.getInt("survey_id");
                    String survey_title = resultSet.getString("survey_title");
                    String survey_desc = resultSet.getString("survey_description");
                    Timestamp last_modification_time = resultSet.getTimestamp("last_modification_time");

                    beanSurveyModule2.setSurveyID(id);
                    beanSurveyModule2.setSurveyTitle(survey_title);
                    beanSurveyModule2.setSurveyDesc(survey_desc);
                    beanSurveyModule2.setLastModifiedTime(last_modification_time);

                    beanSurveyModulesList.add(beanSurveyModule2);
                }

                close();
            } catch (SQLException ex) {
                Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return beanSurveyModulesList;
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

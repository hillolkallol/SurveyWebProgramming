/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.db;

import com.survey.models.BeanQuestionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ratul
 */
public class QuestionsTable {

    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

/*    public boolean insertIntoQuestion(BeanQuestionModule beanQuestionModule) {
        String sqlQuery = "insert into "
        connection = MySQLConnection.connect();
        boolean result = false;
        
        try
        {
            preparedStatement = connection.prepareCall(sql)
        }
        catch(Exception ex)
        {
            
        }
        return false;

    }*/
}

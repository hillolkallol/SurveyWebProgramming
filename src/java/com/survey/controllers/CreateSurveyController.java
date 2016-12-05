/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.survey.db.MySQLConnection;
import com.survey.models.BeanOption;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KD
 */
@WebServlet(name = "CreateSurveyController", urlPatterns = {"/create_survey"})
public class CreateSurveyController extends HttpServlet {

    String labelSurveyTitle = "survey_title";
    String labelSurveyDesc = "survey_desc";
    String labelQuestion = "question";
    String labelOption1 = "option1";
    String labelOption2 = "option2";
    String labelOption3 = "option3";
    String labelOption4 = "option4";
    String labelOption5 = "option5";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //LoginFilter lf = new LoginFilter();
        //lf.isSession(request, response, "create_survey");

        String questions[] = request.getParameterValues(labelQuestion);
        String option1s[] = request.getParameterValues(labelOption1);
        String option2s[] = request.getParameterValues(labelOption2);
        String option3s[] = request.getParameterValues(labelOption3);
        String option4s[] = request.getParameterValues(labelOption4);
        String option5s[] = request.getParameterValues(labelOption5);
        String surveyTitle = request.getParameter(labelSurveyTitle);
        String surveyDesc = request.getParameter(labelSurveyDesc);
        
        
        List<BeanQuestionModule> allQuestion = getAllQuestionFromReq(questions, option1s, option2s, option3s, option4s, option5s);
        
        BeanSurveyModule survey = new BeanSurveyModule();
        survey.setSurveyTitle(surveyTitle);
        survey.setSurveyDesc(surveyDesc);
        survey.setQuestionModules(allQuestion);
        
         //mysqlConnection = new MySQLConnection();
        
        
    }

    private List<BeanQuestionModule> getAllQuestionFromReq(String[] questions, String[] option1s, String[] option2s, String[] option3s, String[] option4s, String[] option5s) {
        List<BeanQuestionModule> allQuestionOptions = new ArrayList<>();
        for (int cnt = 0; cnt < questions.length; cnt++) {
            
            BeanQuestionModule beanQuestionModule = new BeanQuestionModule();            
            beanQuestionModule.setQuestionDesc(questions[cnt]);
            
            BeanOption options = new BeanOption(); 
            List<BeanOption> tmpOptions = new ArrayList<>();
            
            options.setOptionDesc(option1s[cnt]);
            tmpOptions.add(0,options);
            options = new BeanOption(); 
            options.setOptionDesc(option2s[cnt]);
            tmpOptions.add(1,options);
            options = new BeanOption(); 
            options.setOptionDesc(option3s[cnt]);
            tmpOptions.add(2,options);
            options = new BeanOption(); 
            options.setOptionDesc(option4s[cnt]);
            options = new BeanOption(); 
            tmpOptions.add(3,options);
            options = new BeanOption(); 
            options.setOptionDesc(option5s[cnt]);
            tmpOptions.add(4,options);
                       
            
            beanQuestionModule.setOptions(tmpOptions);
            
            allQuestionOptions.add(beanQuestionModule);

        }
        return allQuestionOptions;
    }
}

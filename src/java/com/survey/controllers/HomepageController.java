/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import com.survey.db.OptionsTable;
import com.survey.db.QuestionsTable;
import com.survey.db.SurveyTable;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KD
 */
//@WebServlet(name = "HomepageController", urlPatterns = {"/homepage"})
public class HomepageController extends HttpServlet {

    BeanSurveyModule beanSurveyModule;
    SurveyTable surveyTable;
    QuestionsTable questionsTable;
    OptionsTable optionTable;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);
        if(ses != null){
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

/*        
        if (action.equals("first_hot")) {

            try {
                String strSurveyId = request.getParameter("first_hot_id");
                Long surveyId = Long.parseLong(strSurveyId);
                redirectToRunningSurvey(surveyId, request, response);
            } catch (NumberFormatException ex) {
                System.out.print("Conversion problem at first_hot_id");
            }

        } else if (action.equals("second_hot")) {
            try {

                String strSurveyId  = request.getParameter("second_hot_id");
                Long surveyId = Long.parseLong(strSurveyId);
                redirectToRunningSurvey(surveyId, request, response);
            } catch (NumberFormatException ex) {
                System.out.print("Conversion problem at second_hot_id");
            }
        }
*/
    }

    private void redirectToRunningSurvey(long surveyId, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        surveyTable = new SurveyTable();
        questionsTable = new QuestionsTable();
        optionTable = new OptionsTable();

        try {
            //Get survey details
            beanSurveyModule = surveyTable.selectSurveyById(surveyId);
            //Get all question of that survey
            List<BeanQuestionModule> allQuestionOfSurvey = questionsTable.selectAllQuestionsById(surveyId);

            //Select all options of each question
            List<BeanQuestionModule> questionModules = new ArrayList<>();
            for (BeanQuestionModule eachQsn : allQuestionOfSurvey) {
                BeanQuestionModule question = optionTable.selectAllOptionsByQuestion(eachQsn);
                questionModules.add(question);
            }
            beanSurveyModule.setQuestionModules(questionModules);
            System.out.println(allQuestionOfSurvey.size());

            //beanSurveyModule.
            //request.setAttribute("beanSurveyModule", beanSurveyModule);
            //request.setAttribute("beanSurveyModule", beanSurveyModule);
            //request.getRequestDispatcher("running_survey.jsp").forward(request, response);
            request.setAttribute("beanSurveyModule", beanSurveyModule);
            request.setAttribute("numberOfQuestion", "this is size");
            RequestDispatcher view = request.getRequestDispatcher("running_survey.jsp");
            view.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(RunningSurvey.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

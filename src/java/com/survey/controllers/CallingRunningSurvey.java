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
import java.io.PrintWriter;
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

/**
 *
 * @author Ratul
 */
@WebServlet(name = "TestDispatcher", urlPatterns = {"/TestDispatcher"})
public class CallingRunningSurvey extends HttpServlet {

    BeanSurveyModule beanSurveyModule;
    SurveyTable surveyTable;
    QuestionsTable questionsTable;
    OptionsTable optionTable;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //long surveyId = Long.parseLong(request.getParameter("survey_id"));
        long surveyId = 16;

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
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

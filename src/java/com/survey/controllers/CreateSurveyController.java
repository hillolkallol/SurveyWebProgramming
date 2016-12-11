/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;


import com.survey.db.OptionsTable;
import com.survey.db.QuestionsTable;
import com.survey.db.SurveyTable;
import com.survey.db.UserTable;
import com.survey.models.BeanOptionModule;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
import com.survey.models.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CreateSurveyController", urlPatterns = {"/create_survey"})
public class CreateSurveyController extends HttpServlet {

    private static final String labelSurveyTitle = "survey_title";
    private static final String labelSurveyDesc = "survey_desc";
    private static final String labelQuestion = "question";
    private static final String labelOption1 = "option1";
    private static final String labelOption2 = "option2";
    private static final String labelOption3 = "option3";
    private static final String labelOption4 = "option4";
    private static final String labelOption5 = "option5";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //LoginFilter lf = new LoginFilter();
        //lf.isSession(request, response, "create_survey");
        java.util.Date date = new Date();
        java.sql.Timestamp dateTimeNow = new java.sql.Timestamp(date.getTime());

        String questions[] = request.getParameterValues(labelQuestion);
        String option1s[] = request.getParameterValues(labelOption1);
        String option2s[] = request.getParameterValues(labelOption2);
        String option3s[] = request.getParameterValues(labelOption3);
        String option4s[] = request.getParameterValues(labelOption4);
        String option5s[] = request.getParameterValues(labelOption5);
        String surveyTitle = request.getParameter(labelSurveyTitle);
        String surveyDesc = request.getParameter(labelSurveyDesc);

        List<BeanQuestionModule> allQuestion = getAllQuestionFromReq(questions, option1s, option2s, option3s, option4s, option5s);

        HttpSession ses = request.getSession(false);
        User u = (User) ses.getAttribute("user");
        UserTable usertable = new UserTable();
        
        BeanSurveyModule survey = new BeanSurveyModule();
        survey.setSurveyTitle(surveyTitle);
        survey.setSurveyDesc(surveyDesc);
        survey.setQuestionModules(allQuestion);
        survey.setLastModifiedTime(dateTimeNow);
        survey.setPublishedTime(dateTimeNow);
        survey.setUserID(usertable.findUserID(u.getUsername()));

        SurveyTable surveyTable = new SurveyTable();
        QuestionsTable questionTable = new QuestionsTable();
        OptionsTable optionsTable = new OptionsTable();
        //Insert into survey_details table
        try {
            surveyTable.insertIntoSurveyTable(survey);
            questionTable.insertIntoQuestionTable(survey);
            optionsTable.insertIntoOptionTableQnModule(survey);
        } catch (SQLException ex) {
            Logger.getLogger(CreateSurveyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("/apollo.10/SurveyWebProgramming/dashboard");

    }

    private List<BeanQuestionModule> getAllQuestionFromReq(String[] questions, String[] option1s, String[] option2s, String[] option3s, String[] option4s, String[] option5s) {
        List<BeanQuestionModule> allQuestionOptions = new ArrayList<>();
        for (int cnt = 0; cnt < questions.length; cnt++) {

            BeanQuestionModule beanQuestionModule = new BeanQuestionModule();
            beanQuestionModule.setQuestionDesc(questions[cnt]);

            List<BeanOptionModule> tmpOptions = new ArrayList<>();

            BeanOptionModule options = new BeanOptionModule();
            options.setOptionDesc(option1s[cnt]);
            tmpOptions.add(0, options);

            options = new BeanOptionModule();
            options.setOptionDesc(option2s[cnt]);
            tmpOptions.add(1, options);

            options = new BeanOptionModule();
            options.setOptionDesc(option3s[cnt]);
            tmpOptions.add(2, options);

            options = new BeanOptionModule();
            options.setOptionDesc(option4s[cnt]);
            tmpOptions.add(3, options);

            options = new BeanOptionModule();
            options.setOptionDesc(option5s[cnt]);
            tmpOptions.add(4, options);

            beanQuestionModule.setOptions(tmpOptions);

            allQuestionOptions.add(beanQuestionModule);

        }
        return allQuestionOptions;
    }
}

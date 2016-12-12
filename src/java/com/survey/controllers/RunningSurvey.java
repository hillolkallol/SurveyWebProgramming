/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import com.survey.db.OptionsTable;
import com.survey.db.ParticipationTable;
import com.survey.db.QuestionsTable;
import com.survey.db.SurveyTable;
import com.survey.db.UserSelectionOptionTable;
import com.survey.db.UserTable;
import com.survey.models.BeanOptionModule;
import com.survey.models.BeanQuestionModule;
import com.survey.models.BeanSurveyModule;
import com.survey.models.BeanUserSelectionOption;
import com.survey.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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
 * @author Ratul
 */
@WebServlet(name = "RunningSurvey", urlPatterns = {"/running_survey", "/homepage", "/survey_result_primary"})
public class RunningSurvey extends HttpServlet {

    BeanSurveyModule beanSurveyModule;
    SurveyTable surveyTable;
    QuestionsTable questionsTable;
    OptionsTable optionTable;
    BeanUserSelectionOption beanUserSelectionOption;
    UserSelectionOptionTable userSelectionOptionTable;
    User user;
    int user_id_extra = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);
        user = (User) ses.getAttribute("user");
        
        UserTable usertable = new UserTable();
        user_id_extra = usertable.findUserID(user.getUsername());
        
        if (ses != null) {
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
        /*
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
            RequestDispatcher view = request.getRequestDispatcher("running_survey.jsp");
            view.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(RunningSurvey.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        beanSurveyModule = (BeanSurveyModule) request.getAttribute("beanSurveyModule");
        String size = (String) request.getAttribute("numberOfQuestion");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);
        user = (User) ses.getAttribute("user");
        
        UserTable usertable = new UserTable();
        user_id_extra = usertable.findUserID(user.getUsername());
        
        String action = request.getParameter("hidden_action");

        switch (action) {
            case "first_hot": {
                try {
                    String strSurveyId = request.getParameter("first_hot_id");
                    Long surveyId = Long.parseLong(strSurveyId);
                    redirectToRunningSurvey(surveyId, request, response);
                } catch (NumberFormatException ex) {
                    System.out.print("Conversion problem at first_hot_id");
                }
                break;
            }
            case "second_hot": {
                try {
                    String strSurveyId = request.getParameter("second_hot_id");
                    Long surveyId = Long.parseLong(strSurveyId);
                    redirectToRunningSurvey(surveyId, request, response);
                } catch (NumberFormatException ex) {
                    System.out.print("Conversion problem at second_hot_id");
                }
                break;
            }
            case "running_survey_finish": {
                Enumeration<String> parameterNames = request.getParameterNames();
                List<String> submittedOptions = new ArrayList<>();
                long surveyID = Long.parseLong(request.getParameter("survey_id"));
                while (parameterNames.hasMoreElements()) {
                    String key = parameterNames.nextElement();
                    String parametrValue = request.getParameter(key);
                    submittedOptions.add(parametrValue);
                    System.err.println("Valueeee" + parametrValue);
                }
                submittedOptions.remove(0);
                submittedOptions.remove(0);
                submittedOptions.remove(submittedOptions.size() - 1);
                List<BeanUserSelectionOption> relativeOptions = convertSubmittedParamSelectionBean(submittedOptions, surveyID);
                userSelectionOptionTable = new UserSelectionOptionTable();
                try {
                    userSelectionOptionTable.insertIntoUserSelectionTableByList(relativeOptions);
                } catch (SQLException ex) {
                    Logger.getLogger(RunningSurvey.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "result_survey": {
                Enumeration<String> parameterNames = request.getParameterNames();
                List<String> submittedOptions = new ArrayList<>();
                long surveyID = Long.parseLong(request.getParameter("survey_id"));
                while (parameterNames.hasMoreElements()) {
                    String key = parameterNames.nextElement();
                    String parametrValue = request.getParameter(key);
                    submittedOptions.add(parametrValue);
                    System.err.println("Valueeee" + parametrValue);
                }
                submittedOptions.remove(0);
                submittedOptions.remove(0);
                submittedOptions.remove(submittedOptions.size() - 1);
                List<BeanUserSelectionOption> relativeOptions = convertSubmittedParamSelectionBean(submittedOptions, surveyID);
                userSelectionOptionTable = new UserSelectionOptionTable();
                try {
                    userSelectionOptionTable.insertIntoUserSelectionTableByList(relativeOptions);
                } catch (SQLException ex) {
                    Logger.getLogger(RunningSurvey.class.getName()).log(Level.SEVERE, null, ex);
                }
                redirectToFinishSurvey(request, response);
                
            }
            default: {

                break;
            }
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

    private List<BeanUserSelectionOption> convertSubmittedParamSelectionBean(List<String> values, Long surveyId) {

        List<BeanUserSelectionOption> beanUserSelectionOptions = new ArrayList<>();
        for (String value : values) {
            BeanUserSelectionOption beanOption = new BeanUserSelectionOption();
            String[] temp = value.split("_");
            String tmpQuestionId = temp[0];
            String tmpOptionId = temp[1];
            tmpOptionId = tmpOptionId.replaceAll("\\s+", "");
            beanOption.setQuestionId(Long.parseLong(tmpQuestionId));
            beanOption.setOptionId(Long.parseLong(tmpOptionId));
            beanOption.setSurveyId(surveyId);

            //beanOption.setUserId(1); /////////------------------------------------------
            beanOption.setUserId(user_id_extra);

            beanUserSelectionOptions.add(beanOption);
        }
        return beanUserSelectionOptions;
    }

    private void redirectToFinishSurvey(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {

            //Get options
            userSelectionOptionTable = new UserSelectionOptionTable();
            userSelectionOptionTable.selectNumberOfUserBySurvey(beanSurveyModule);
            int numberOfUser = userSelectionOptionTable.selectNumberOfUserOfSurveyId(beanSurveyModule.getSurveyID());
            //--get options
            request.setAttribute("beanSurveyModule", beanSurveyModule);
            request.setAttribute("numberOfUser", numberOfUser);
            
            HttpSession ses = request.getSession(false);
            user = (User) ses.getAttribute("user");

            UserTable usertable = new UserTable();
            user_id_extra = usertable.findUserID(user.getUsername());
            
            ParticipationTable participationTable = new ParticipationTable();
            participationTable.insertIntoParrticipantionTable(beanSurveyModule.getSurveyID(),user_id_extra);

            RequestDispatcher view = request.getRequestDispatcher("survey_result_primary.jsp");
            view.forward(request, response);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(RunningSurvey.class.getName()).log(Level.SEVERE, null, ex);
        }
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

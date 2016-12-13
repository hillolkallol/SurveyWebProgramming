/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import com.survey.db.ParticipationTable;
import com.survey.db.SurveyTable;
import com.survey.db.UserTable;
import com.survey.models.BeanSurveyModule;
import com.survey.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "DashboardController", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {

    List<BeanSurveyModule> beanSurveyModuleModel;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanSurveyModule sendBean = new BeanSurveyModule();
        int counter = 0;
        if(request.getParameter("counter") != null)
            counter = Integer.parseInt(request.getParameter("counter"));
        sendBean = beanSurveyModuleModel.get(counter);

        RunningSurvey runningSurvey = new RunningSurvey();
        runningSurvey.fromDashboard(sendBean, request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);

        //JOptionPane.showMessageDialog(null, ses.getAttribute("user"));
//        UserTable usertable = new UserTable();
//        User userDetails = new User();
        User u = (User) ses.getAttribute("user");
//        userDetails = usertable.userInfo(u.getUsername());
//        request.setAttribute("userDetails", userDetails);

        if (ses != null) {
            createdByMe(u, request, response);
            participatedByMe(u, request, response);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    public Timestamp getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        Timestamp strDate = Timestamp.valueOf(sdfDate.format(now));
        return strDate;
    }

    private List<String> statusButton(List<BeanSurveyModule> beanSurveyModuleModel) {
        List<String> status = new ArrayList<>();
        for (int i = 0; i < beanSurveyModuleModel.size(); i++) {
            if (beanSurveyModuleModel.get(i).getPublishedTime().before(getCurrentTimeStamp())) {
                status.add("Unpublish");
            } else {
                status.add("publish Now");
            }
        }
        return status;
    }

    private void createdByMe(User u, HttpServletRequest request, HttpServletResponse response) {
        beanSurveyModuleModel = new ArrayList<>();
        SurveyTable surveyTable = new SurveyTable();
        ParticipationTable participationTable = new ParticipationTable();
        beanSurveyModuleModel = surveyTable.selectFromSurveyTable(u.getUsername());
        request.setAttribute("beanSurveyModuleModel", beanSurveyModuleModel);
        request.setAttribute("participent", participationTable.totalParticipent(beanSurveyModuleModel));
        request.setAttribute("change_status_option", statusButton(beanSurveyModuleModel));
    }

    private void participatedByMe(User u, HttpServletRequest request, HttpServletResponse response) {
        beanSurveyModuleModel = new ArrayList<>();
        SurveyTable surveyTableP = new SurveyTable();
        ParticipationTable participationTableP = new ParticipationTable();
        UserTable userTable = new UserTable();
        List<Integer> allSurveyID = new ArrayList<>();
        allSurveyID = participationTableP.allSurveyID(userTable.findUserID(u.getUsername()));
        beanSurveyModuleModel = surveyTableP.surveyDetailsParticipatedByMe(allSurveyID);
        request.setAttribute("beanSurveyModuleModelParticipatedByMe", beanSurveyModuleModel);
        request.setAttribute("participentParticipatedByMe", participationTableP.totalParticipent(beanSurveyModuleModel));

    }
}

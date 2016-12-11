/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import com.survey.db.UserTable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.survey.models.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KD
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out = response.getWriter();
        request.setAttribute("login_error_msg", "");
        HttpSession ses = request.getSession(false);
        if (ses != null) {
            if(ses.getAttribute("user") != null){
                //out.println("not null");
                //request.getRequestDispatcher(page).forward(request, response);
                response.sendRedirect("/apollo.10/SurveyWebProgramming/homepage");
            }
            else {
                //out.println("null");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                //response.sendRedirect("login?request="+request+"&response="+response);
            }
        } else {
            //out.println("session null");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

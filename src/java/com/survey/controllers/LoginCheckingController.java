/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import com.survey.db.UserTable;
import com.survey.models.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LoginCheckingController", urlPatterns = {"/login_checking"})
public class LoginCheckingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String username = request.getParameter("USERNAME");
        String password = request.getParameter("PASSWORD");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        UserTable usertable = new UserTable();
        boolean b = usertable.login(user);
        
        if(b){
            request.setAttribute("user", user);
            HttpSession session =request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/apollo.10/SurveyWebProgramming/homepage");
        }
        else {
            request.setAttribute("login_error_msg", "Invalid Username/Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

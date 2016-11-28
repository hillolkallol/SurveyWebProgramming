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
@WebServlet(name = "LoginController", urlPatterns = {"/index"})
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String username = request.getParameter("USERNAME");
        String password = request.getParameter("PASSWORD");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        UserTable usertable = new UserTable();
        boolean b = usertable.login(user, sql);
        
        if(b){
            //request.setAttribute("user", user);
            HttpSession session =request.getSession();
            session.setAttribute("user", user.getUsername());
            RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
            dispatcher.forward(request, response);
            //response.sendRedirect("homepage.jsp");
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);
        if(ses == null)
            request.getRequestDispatcher("login.jsp").forward(request, response);
        else
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
    

}

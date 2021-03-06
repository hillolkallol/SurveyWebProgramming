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

/**
 *
 * @author KD
 */
@WebServlet(name = "RecoveredController", urlPatterns = {"/recovered"})
public class RecoveredController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String email = request.getParameter("email");
                
        if(pass.equals(repass)) {
            User userDetails = new User();
        
            userDetails.setPassword(pass);
            userDetails.setEmail_address(email);
            
            UserTable usertable = new UserTable();
            String s = "";
            s = usertable.resetPassword(userDetails);
            request.setAttribute("update_message", s);
            response.sendRedirect("login");
            //request.getRequestDispatcher("recovery.jsp").forward(request, response);
            //doGet(request, response);
        }
        else {
            request.setAttribute("update_message", "Old Password doesn't match!");
            response.sendRedirect("login");
            //request.getRequestDispatcher("recovery.jsp").forward(request, response);
            //doGet(request, response);
        }
        
    }
}

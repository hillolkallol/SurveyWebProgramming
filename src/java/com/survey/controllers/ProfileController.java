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
import javax.swing.JOptionPane;

/**
 *
 * @author KD
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);
        
        //JOptionPane.showMessageDialog(null, ses.getAttribute("user"));
        UserTable usertable = new UserTable();
        User userDetails = new User();
        User u = (User) ses.getAttribute("user");
        userDetails = usertable.userInfo(u.getUsername());
        request.setAttribute("userDetails", userDetails);
        
        if(ses != null){
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/apollo.10/SurveyWebProgramming/login");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        String f_name = request.getParameter("f_name");
        String l_name = request.getParameter("l_name");
        String old_pass = request.getParameter("old_pass");
        String new_pass = request.getParameter("re_pass");
        
        HttpSession ses = request.getSession(false);
        User u = (User) ses.getAttribute("user");
        
        if(old_pass.equals(u.getPassword())) {
            User userDetails = new User();
        
            userDetails.setFirst_name(f_name);
            userDetails.setLast_name(l_name);
            userDetails.setEmail_address(email);
            userDetails.setUsername(u.getUsername());
            if(new_pass == null)
                userDetails.setPassword(old_pass);
            else
                userDetails.setPassword(new_pass);
            
            UserTable usertable = new UserTable();
            String s = "";
            s = usertable.updateUserInfo(userDetails);
            request.setAttribute("update_message", s);
            doGet(request, response);
        }
        else {
            request.setAttribute("update_message", "Old Password doesn't match!");
            doGet(request, response);
        }
        
    }
}

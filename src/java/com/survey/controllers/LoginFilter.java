/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survey.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author KD
 */
public class LoginFilter {

    public void isSession (HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {    
        HttpSession ses = request.getSession(false);
        if(ses != null)
            request.getRequestDispatcher(page).forward(request, response);
        else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

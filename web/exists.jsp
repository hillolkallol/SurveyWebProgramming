<%-- 
    Document   : exists
    Created on : Nov 28, 2016, 2:16:10 PM
    Author     : bakir
--%>

<%@page import="com.survey.db.MySQLConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try{
        Class.forName("com.mysql.jdbc.Driver");
        //java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", "root", "");
        java.sql.Connection con = MySQLConnection.connect();
            PreparedStatement ps = con.prepareStatement("SELECT  * FROM users WHERE " +
                    "email_address = ?");
            ps.setString(1,request.getParameter("email"));
            ResultSet res = ps.executeQuery(); 
            if(res.first()){
                out.print("User already exists");
            }else{
                out.print("User name is available");
            }
        }catch (Exception e){
            System.out.println(e);  
        }
%>

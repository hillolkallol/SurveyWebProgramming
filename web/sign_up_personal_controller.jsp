<%-- 
    Document   : sign_up_personal
    Created on : Nov 27, 2016, 3:07:07 PM
    Author     : bakir
    
--%>

<%@page import="com.survey.db.MySQLConnection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            String userName = request.getParameter("userName");
            String date = request.getParameter("dateOfBirth");
            String gender = request.getParameter("gender");
            String country = request.getParameter("country");

            int sate = 1;//request.getParameter("sate");

            // Now get the values stored in the session variable.
            String firstName = (String) session.getAttribute("first_name");
            String lastName = (String) session.getAttribute("last_name");
            String email = (String) session.getAttribute("email");
            String pass = (String) session.getAttribute("password");
            try {
                // Now Save these value into database.
//            Class.forName("com.mysql.jdbc.Driver");
//            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apollo10_survey_system", "apollo10", "udGo1Qrt3L");
                java.sql.Connection con = MySQLConnection.connect();
                if (true == con.isClosed()) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                Statement st = con.createStatement();

                String startDate = "01-02-2013";
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
                java.util.Date date1 = sdf1.parse(date);
                java.sql.Date myBirthDate = new java.sql.Date(date1.getTime());

                // String sql = "INSERT INTO users " +
                //       "VALUES (4,'"+(String)session.getAttribute("first_name")+"', '"+(String)session.getAttribute("last_name")+"', '"+(String)session.getAttribute("email")+"',"
                //        + "'"+(String)session.getAttribute("password")+"','"+request.getParameter("gender")+"','"+currentTime+"','"+request.getParameter("sate")+"')";
                // st.execute(sql);
                String sql = "INSERT INTO users " + "(first_name,last_name,username,email_address,password,gender,date_of_birth,state_id)"
                        + "VALUES ('" + firstName + "', '" + lastName + "', '" + userName + "','" + email + "','" + pass + "','" + gender + "','" + myBirthDate + "','" + sate + "')";
                st.execute(sql);
                session.invalidate();
                response.sendRedirect("login");
                

            } catch (SQLException se) {
                //Handle errors for JDBC
//                se.printStackTrace();
                String message = "Something goes wrong!";
                    request.setAttribute("error_message", message);
                    request.getRequestDispatcher("sign_up_primary.jsp").forward(request, response);
            }
        %>        
    </head>
    <body>
    </body>
</html>

<%-- 
    Document   : login
    Created on : Nov 26, 2016, 6:29:43 PM
    Author     : bakir
--%>
<%@page import="java.sql.*;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
                    String userid = request.getParameter("usr");
                    session.putValue("userid", userid);
                    String pwd = request.getParameter("pwd");
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", "root", "");
                    if(true == con.isClosed())
                    {
                        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    }
                    else
                    {
                    Statement st = con.createStatement();
                   
                    ResultSet rs = st.executeQuery("SELECT * from users where email_address='"+userid+"'");
                    if(rs.next())
                    {
                        if(rs.getString(5).equals(pwd))
                        {
                            session.setAttribute("username", request.getParameter("username"));
                            response.sendRedirect("homepage.html");
                        }
                        else
                        {
                            session.invalidate();
                            request.setAttribute("errorMessage", "Invalid user or password");
                            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                            rd.forward(request, response);          
                        }
                        
                    }
                    else
                    {
                        session.invalidate();
                        request.setAttribute("errorMessage", "Invalid user or password");
                        RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                        rd.forward(request, response);

                    }
                    }
            %>
    </body>
</html>

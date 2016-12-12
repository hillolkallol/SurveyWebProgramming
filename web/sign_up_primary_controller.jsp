<%-- 
    Document   : sign_up_primary
    Created on : Nov 27, 2016, 2:36:34 PM
    Author     : bakir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");
            String reemail = request.getParameter("re-email");
            if(!email.equals(reemail))
            {%>
                        echo "<SCRIPT LANGUAGE=\"JavaScript\">";
			echo "alert(\"You didnt Fill out Location! Go Back and fillup.\")";
			echo "</script> ";
			echo "<script language=\"JavaScript\">";
			echo "history.back()";
			echo "</script>"; }<%
            }
            String pass = request.getParameter("password");
            String repass = request.getParameter("re-password");
            if(!pass.equals(repass))
            {
                // Send error message.
            }
            // Now we need to save these variable into session object to process later. This is the way I know, can
            //be some better way. change it if you want.
            session.setAttribute("first_name", firstName);
            session.setAttribute("last_name", lastName);
            session.setAttribute("email", email);
            session.setAttribute("password", pass);
            
            // Now redirect to next pages.
            // response.sendRedirect("sign_up_personal.html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("sign_up_personal.jsp");
            dispatcher.forward(request, response);
            
            
        %>
        
    </head>
    <body>
    </body>
</html>

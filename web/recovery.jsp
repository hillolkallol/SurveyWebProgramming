<%-- 
    Document   : recovery
    Created on : Dec 1, 2016, 5:55:18 PM
    Author     : KD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% String message = " ";
    if((String) request.getAttribute("pass_recovery") != null)
        message = (String) request.getAttribute("pass_recovery");  
%> 
<!DOCTYPE html>
<html lang="en">
    <head>

        <!-- bootstrap file -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width = device-width, initial-scale = 1">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- bootstrap file -->
    </head>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section">
                <div class="panel-heading"><img class="logo" src="img/logo.png"><br><br>
                    <h2 >Reset Password</h2></div>
                <form method="post" action="recovered" class="panel-body">
                    <input class="form-control" placeholder="new password" type="password" name="pass" required><br>
                    <input class="form-control" placeholder="re-type passwod" type="password" name="repass" required><br>
                    <input type="text" name="email" value="${email}" hidden>
                    <label><%= message %></label><br>
                    <input class="btn btn-primary" type="submit" value="Reset"><br>
                </form>
                <a class="customize_link" href="${pageContext.request.contextPath}/login">Back to home?</a>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>


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
        <title>Polling</title>

        <meta charset="UTF-8">
        <meta name="description" content="Polling web application">
        <meta name="keywords" content="Polling, Voting, Feedback">
        <meta name="author" content="bdTigers">
        <link rel="stylesheet" type="text/css" href="style.css">

    </head>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section">
                <img class="logo" src="img/logo.png"><br><br>
                <h2>Reset Password</h2><br>
                <form method="post" action="recovery">
                    <label>New Password: </label><input type="password" name="pass" required><br>
                    <label>Re-type Password: </label><input type="password" name="repass" required><br>
                    <input type="text" name="email" value="${email}" hidden>
                    <label><%= message %></label><br>
                    <input type="submit" value="Reset"><br>
                </form>
                <a class="customize_link" href="${pageContext.request.contextPath}/login">Back to home?</a>
            </div>
        </div>
        <div id="footer" class="fixed_footer">
            <div class="footer_body">
                <table class="footer_table">
                    <tr>
                        <td><a class="customize_link" href="about.html">About</a></td>
                        <td><a class="customize_link" href="copyright.html">Copyright</a></td>
                        <td><a class="customize_link" href="terms.html">Terms and Conditions</a></td>
                        <td><a class="customize_link" href="help.html">Helps</a></td>
                        <td><a class="customize_link" href="contact.html">Contact Us</a></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>


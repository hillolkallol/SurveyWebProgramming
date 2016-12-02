<%-- 
    Document   : forget_password
    Created on : Nov 30, 2016, 11:11:38 PM
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
                <h2>Forget Password?</h2><br>
                <p>A password recovery link will be sent to your Email address after submitting!</p><br>
                <form method="post" action="forget_password">
                    <label>Username/Email: </label><input type="text" name="email_or_username" required><br>
                    <label><%= message %></label><br>
                    <input type="submit" value="Recover"><br>
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

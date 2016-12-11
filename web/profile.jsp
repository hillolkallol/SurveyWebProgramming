<%-- 
    Document   : profile
    Created on : Nov 28, 2016, 10:03:35 PM
    Author     : KD
--%>

<%@page import="com.survey.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- User userDetails = new User();
    userDetails = (User) request.getAttribute("userDetails");  --%> 
<% String message = " ";
    if((String) request.getAttribute("update_message") != null)
        message = (String) request.getAttribute("update_message");  
%> 
<!DOCTYPE html>

<html>

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
            <%@include file="header.jsp" %>
		<div class="body min_height">
			<form method="post" action="profile" class="panel-body">
                            <table class="profile_body_section profile_created_by_me_table">
                                <tr>
                                    <td>Email</td>
                                    <td><input class="form-control" type="text" name="email" value="${userDetails.getEmail_address()}" required></td>
                                </tr>
                                <tr>
                                    <td>Username</td>
                                    <td><input class="form-control" type="text" name="user_name" value="${userDetails.getUsername()}" disabled></td>
                                </tr>
                                <tr>
                                    <td>First Name</td>
                                    <td><input class="form-control" type="text" name="f_name" value="${userDetails.getFirst_name()}" required></td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td><input class="form-control" type="text" name="l_name" value="${userDetails.getLast_name()}" required></td>
                                </tr>
                                <tr>
                                    <td>Old Password</td>
                                    <td><input class="form-control" type="password" name="old_pass" value="" required></td>
                                </tr>
                                <tr>
                                    <td>New Password</td>
                                    <td><input class="form-control" type="password" placeholder="Keep BLANK if you don't want to change the password!" name="new_pass" value=""></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><label><%= message %></label></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input class="btn btn-primary" type="submit" name="save" value="Save"></td>
                                </tr>
                            </table>
			</form>
		</div>
		<%@include file="footer.jsp" %>
	</body>

</html>
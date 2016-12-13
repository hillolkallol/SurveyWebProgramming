<%@page import="com.survey.db.MySQLConnection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>

	<head>
	</head>

	<body>
            <%
            ResultSet res = null;
            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", "root", "");
                java.sql.Connection con = MySQLConnection.connect();
                String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
                 
                 Statement st = con.createStatement();
                   
                 res = st.executeQuery("SELECT  * FROM survey_details WHERE "
                 + "publish_time <='"+currentTime+"'");
                
            } catch (Exception e) {
                System.out.println(e);
            }
            %>
		<%@include file="header.jsp" %>
		<div class="body">
			<section class="homepage_body_section">
                            <%while(res.next()){%>
                                <form action="homepage" method="post">
                                <input type="text" name="hidden_action" value="first_hot" hidden>
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
								<input type="hidden" value="<%= res.getString(1)%>" name = "first_hot_id">
                                        <h1><input type="submit" class="link-button" name="second_hot" value="<%= res.getString(2)%>"> </h1>
								<p><%= res.getString(3) %></p>
							</div>
							<div class="section_item_part3">
								Date: <%= res.getString(6) %><br> 
								# of Participant
							</div>
						</div>
					</section>
                                                                </form>
				<%}%>
			</section>
		</div>
		<%@include file="footer.jsp" %>
	</body>

</html>
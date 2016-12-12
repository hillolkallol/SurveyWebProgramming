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
				<a href="running_survey.jsp">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
								<h2><%= res.getString(2) %></h2>
								<p><%= res.getString(3) %></p>
							</div>
							<div class="section_item_part3">
								Date: <%= res.getString(6) %><br> 
								# of Participant
							</div>
						</div>
					</section>
				</a> <%}%>
			</section>
		</div>
		<%@include file="footer.jsp" %>
	</body>

</html>
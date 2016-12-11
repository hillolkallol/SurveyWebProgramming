
<%@page import="com.survey.db.MySQLConnection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<!DOCTYPE html>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%@page import="com.survey.models.User" %>

<html>

	<head>
		
	</head>

	<body>
            <%
                // Get the current data from the survey table.
             ResultSet res = null;
             ResultSet rs = null;
             String running1 = "";
             String runTitle = "";
             String runDesc = "";
             ResultSet upRes = null;
             try {
//                Class.forName("com.mysql.jdbc.Driver");
//                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", "root", "");
                java.sql.Connection con = MySQLConnection.connect();
                String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

                PreparedStatement ps = con.prepareStatement("SELECT  * FROM survey_details WHERE "
                 + "publish_time <='"+currentTime+"'");
                
                 res = ps.executeQuery();
                 Statement st = con.createStatement();
                 rs = st.executeQuery("SELECT * FROM `survey_details` WHERE `publish_time` <= '"+currentTime+"' ORDER BY survey_id DESC LIMIT 3");
//                 rs = st.executeQuery("SELECT  * FROM survey_details WHERE "
//                 + "publish_time <='"+currentTime+"'");
                 
                 Statement upSt = con.createStatement();
                 upRes = upSt.executeQuery("SELECT * FROM `survey_details` WHERE `survey_id` IN (SELECT COUNT(`user_id`) FROM participation GROUP BY `survey_id`) LIMIT 3");
                 
//                 upRes = upSt.executeQuery("SELECT  * FROM survey_details WHERE "
//                 + "publish_time >='"+currentTime+"'");
                 //upRes.next();
                
                 
                 
                
            } catch (Exception e) {
                System.out.println(e);
            }
                
            %>
		<%@include file="header.jsp" %>
		<div class="body">
                    <section class="homepage_body_section">
                        <%if (rs.next()) {%>
                        <!--<a href="running_survey.html"> -->
                        <section class="section_item">
                            <form action="homepage" method="post">
                                <input type="text" name="hidden_action" value="first_hot" hidden>
                                <div class="section_item_table">
                                    <div class="section_item_part1">
                                        <img class="circular_image" src="img/logo.jpg" alt="logo">
                                    </div>
                                    <div class="section_item_part2">
                                        <input type="hidden" value="<%= rs.getString(1)%>" name = "first_hot_id">
                                        <h1><input type="submit" class="link-button" name="first_hot" value="<%= rs.getString(2)%>"> </h1>

                                        <p><%= rs.getString(3)%></p>
                                    </div>
                                    <div class="section_item_part3">
                                        Date: <%= rs.getString(6)%><br>
                                    </div>
                                </div>
                            </form>
                        </section>
                        <!--</a> --><%}%>
                        <%if (rs.next()) {%> 

                        <section class="section_item">
                            <form action="homepage" method="post">
                                <input type="text" name="hidden_action" value="second_hot" hidden>
                                <div class="section_item_table">
                                    <div class="section_item_part1">
                                        <img class="circular_image" src="img/logo.jpg" alt="logo">
                                    </div>
                                    <div class="section_item_part2">
                                        <input type="hidden" value="<%= rs.getString(1)%>" name = "second_hot_id">
                                        <h1><input type="submit" class="link-button" name="second_hot" value="<%= rs.getString(2)%>"> </h1>

                                        <p><%= rs.getString(3)%></p>
                                    </div>
                                    <div class="section_item_part3">
                                        Date: <%= rs.getString(6)%><br>
                                        
                                    </div>
                                </div>
                            </form>
                        </section>
                        <!--</a> --><%}%>
                        <%if (rs.next()) {%> 

                        <section class="section_item">
                            <form action="homepage" method="post">
                                <input type="text" name="hidden_action" value="second_hot" hidden>
                                <div class="section_item_table">
                                    <div class="section_item_part1">
                                        <img class="circular_image" src="img/logo.jpg" alt="logo">
                                    </div>
                                    <div class="section_item_part2">
                                        <input type="hidden" value="<%= rs.getString(1)%>" name = "second_hot_id">
                                        <h1><input type="submit" class="link-button" name="second_hot" value="<%= rs.getString(2)%>"> </h1>

                                        <p><%= rs.getString(3)%></p>
                                    </div>
                                    <div class="section_item_part3">
                                        Date: <%= rs.getString(6)%><br>
                                        
                                    </div>
                                </div>
                            </form>
                        </section>
                        <!--</a> --><%}%>
                        <a href="see_more.jsp"><input class="btn btn-primary" type="submit" name="see_more" value="See More"></a> 
                    </section>
                </div>
                <%@include file="footer.jsp" %>
	</body>
</html>

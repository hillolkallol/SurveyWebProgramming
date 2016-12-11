<%-- 
    Document   : dashboard
    Created on : Nov 30, 2016, 10:42:53 PM
    Author     : KD
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.survey.models.BeanSurveyModule"%>
<%@page import="com.survey.models.BeanSurveyModule"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="body">
            <section class="dashboard_body_section">
                <h2>Created By Me</h2>
                <table class="dashboard_created_by_me_table">
                    <tr>
                        <th>Title</th>
                        <th>Last Modification Date</th>
                        <th># of Participant</th>
                        <!--<th class="dashboard_created_by_me_table_column4"> Change Status</th>-->
                    </tr>
                    <tr>
                        <td>
                            <c:forEach items="${beanSurveyModuleModel}" var="item">
                                <a href="detailed_view?survey_id=${item.getSurveyID()}">
                                    ${item.getSurveyTitle()}<br>
                                </a>
                            </c:forEach>
                        </td>

                        <td>
                            <c:forEach items="${beanSurveyModuleModel}" var="item">
                                ${item.getLastModifiedTime()}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${participent}" var="item">
                                ${item}<br>
                            </c:forEach>
                        </td>
                        <!--						<td class="dashboard_created_by_me_table_column4">
                                                                            
                        <%--<c:set var="counter" value="0" scope="page"></c:set>
                                                                            <c:forEach items="${beanSurveyModuleModel}" var="item">
                                                                                
                                                                                <form method="post" action="change_status"><input type="text" name="hiddenID" value="${item.getSurveyID()}" ><br>
                                                                                <input class="btn btn-primary" type="submit" name="publsih" value="${change_status_option.get(counter)}">
                                                                                <c:set var="counter" value="${counter + 1}" scope="page"></c:set>
                                                                                </form><br>
                                                                            </c:forEach> --%>
                                                                            
                                                                        </td>-->
                    </tr>
                </table>
            </section>

            <section class="dashboard_body_section">
                <h2>Participated by Me</h2>
                <a href="running_survey.jsp">
                    <section class="section_item">
                        <div class="table-responsive">
                            <table class="table">
                            <tr>
                                    <c:forEach items="${beanSurveyModuleModelParticipatedByMe}" var="item">
                                        
                                        <td class="section_item_part2">
                                            <h2>${item.getSurveyTitle()}</h2>
                                            <p>${item.getSurveyDesc()}</p>
                                        </td>
                                        <td class="section_item_part3">
                                            ${item.getLastModifiedTime()}<br>
                                        </td>
                                        </c:forEach>
                                        <%--
<!--                                        <td>
                                            <c:forEach items="${participentParticipatedByMe}" var="item">
                                                Participant # ${item}<br>
                                            </c:forEach>
                                        </td>--> --%>
                            </tr>

                        </table>
                        </div>
                    </section>
                </a>
            </section>
        </div>
        <%@include file="footer.jsp" %>
    </body>

</html>

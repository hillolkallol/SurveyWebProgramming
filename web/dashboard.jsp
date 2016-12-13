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

        

    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="body">
            <section class="dashboard_body_section">
                <h2>Created By Me</h2>
                
                    <input type="hidden" value="result_survey" name="hidden_action">
                    <table class="dashboard_created_by_me_table">
                        <tr>
                            <th>Title</th>
                            <th>Last Modification Date</th>
                            <th># of Participant</th>
                            <!--<th class="dashboard_created_by_me_table_column4"> Change Status</th>-->
                        </tr>


                        <tr>

                            <td>
                                <c:set var="optionCounter" value="0" scope="page" />
                                <c:forEach items="${beanSurveyModuleModel}" var="item">
                                    <form action="dashboard" method="POST">
                                    <input type="text" name="counter" value="${optionCounter}" hidden>
                                    <input type="submit" class="link-button" value="${item.getSurveyTitle()}" ><br>
                                    </form>
                                    <c:set var="optionCounter" value="${optionCounter + 1}" scope="page"/>
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
                <section class="section_item">
                    <div class="table-responsive">

                        <input type="hidden" value="result_survey" name="hidden_action">
                        <table class="table">
                            <tr>
                                <c:set var="optionCounter" value="0" scope="page" />
                                <c:forEach items="${beanSurveyModuleModelParticipatedByMe}" var="item">

                                    <td class="section_item_part2">
                                        <form action="dashboard" method="POST">
                                            <input type="text" name="counter" value="${optionCounter}" hidden>
                                            <h2><input type="submit" class="link-button" value="${item.getSurveyTitle()}" ></h2>
                                        </form>
                                        <p>${item.getSurveyDesc()}</p>
                                    </td>
                                    <td class="section_item_part3">
                                        ${item.getLastModifiedTime()}<br>
                                    </td>
                                    <c:set var="optionCounter" value="${optionCounter + 1}" scope="page"/>
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
            </section>
        </div>
        <%@include file="footer.jsp" %>
    </body>

</html>

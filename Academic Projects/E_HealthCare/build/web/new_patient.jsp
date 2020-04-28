<%-- 
    Document   : new_patient
    Created on : Mar 27, 2020, 11:58:10 PM
    Author     : prite

    New patient landing page.

--%>

<%@page import="java.util.List"%>
<%@page import="com.neu.model.DoctorList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
</form>

<hr><br><br>
<%@ page session="true"%>
<div class="container">
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance"  user="root"  password="password"/>  
    <% 
        DoctorList rep = new DoctorList();
        rep = (DoctorList)request.getAttribute("doctor_list");
        
        String patient_name = rep.getPatient_name();
        int patient_id = rep.getPatient_id();
        List<String> dname = rep.getDoctor_name();
        List<String> did = rep.getDoctor_id();
        List<String> dcount = rep.getDoctor_app_count();
        
    %>
    
    <h1>Welcome <%= patient_name%> to the E_Healthcare </h1>
    
    <h3>Please make selection from available doctors appointment</h3>
    
    <sql:query dataSource="${db}" var="ps">  
        select * from patient where patient_id = <%= patient_id%>;
    </sql:query>
    
    <c:forEach var="table" items="${ps.rows}">
        <c:set var = "pt_doctor_name" scope = "session" value = "${table.doctor_name}"/>
    </c:forEach>

    <table border="2" width="100%">  
        <tr>  
            <th>Doctor ID</th>  
            <th>Doctor Name</th>  
            <th>Appointment Count</th>
            <th>Make Appointment</th>
        </tr>
         
        <%for(int i=0; i < dname.size();i++){%>
            <tr>  
                <form action="appoint.jsp" method="post">
                    <input type="hidden" name="patient_id" value="<%= patient_id %>" />
                    <input type="hidden" name="patient_name" value="<%= patient_name %>" />
                    <td><input type="text" name="doctor_id" value="<%= did.get(i) %>" readonly="true" /></td>  
                    <td><input type="text" name="doctor_name" value="<%= dname.get(i) %>" readonly="true" /></td>  
                    <td><input type="text" name="doctor_count" value="<%= dcount.get(i) %>" readonly="true" /></td> 
                                       
                    <c:set var = "count" scope = "session" value = "<%= dcount.get(i) %>" />
                    <c:set var = "doctor_name" scope = "session" value = "<%= dname.get(i) %>" />
                    
                    <c:if test="${(sessionScope.count >= 3) or (sessionScope.pt_doctor_name eq sessionScopedoctor_name)}">
                        <td><input type="submit" value="Select" style="background-color: black;" disabled=""/></td>
                    </c:if>
                    <c:if test="${(count < 3) }">
                        <td><input type="submit" value="Select" /></td>
                    </c:if>
                </form>
            </tr>  
        <%}%>
    </table>
    <br><br>
</div>
<%@include file = "footer.html"%>  

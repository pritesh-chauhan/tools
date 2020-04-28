<%-- 
    Document   : doctor
    Created on : Mar 26, 2020, 11:25:23 PM
    Author     : prite

    Doctor dashboard page
    The data is obtained from the DoctorAppoinment DAO object.
--%>

<%@page import="com.neu.model.DoctorAppointment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" style="float: right;"/>
    </form>
</div>
<hr><br><br>

<%    
    DoctorAppointment docapp = new DoctorAppointment();
    docapp = (DoctorAppointment)request.getAttribute("doctor");
    List patient_id = (ArrayList)docapp.getPatient_id();
    List patient_name = (ArrayList)docapp.getPatient_name();
    List message = (ArrayList)docapp.getMessage();
    int doctor_id = docapp.getDoctor_id();
    String doctor_name = (String)docapp.getDoctor_name();
%>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance"  user="root"  password="password"/>  

<div class="header">
    <h1 style="margin-left: 180px;">Welcome Doctor, <%=doctor_name%></h1>

    <form action="excel.jsp" method="post" style="float: right;">
        
        <input type="hidden" name="doctor_name" value= <%= doctor_name%> />
        <input type="hidden" name="doctor_id" value= <%= doctor_id%> />
        <input type="hidden" name="patient_id" value= <%= patient_id%> />
        <input type="hidden" name="patient_name" value= <%= patient_name%> />
        <input type="hidden" name="message" value= <%= message%> />
        <input type="submit" value="Get all Patient's History" />
    </form>
</div>
        
<h2>Below are the patient appointments</h2>

<sql:query dataSource="${db}" var="count">  
    select count(*) as count from illness where doctor_id = <%=doctor_id%>;
</sql:query>
    
<c:forEach var="table" items="${count.rows}">
    <c:set var = "count" scope = "session" value = "${table.count}"/>
</c:forEach>
    
    
<c:if test="${sessionScope.count > 0}"> 
    <table border="2" width="100%" style="text-align: center;"> 

        <tr>
            <th>Patient ID</th>  
            <th>Patient Name</th> 
            <th>Message from Patient</th>
            <th>Prescribe Medicines</th>
        </tr>

        <%for(int i=0; i < patient_id.size();i++){%>
        <tr>  
        <form action="PrescribeController" method="post">
                <input type="hidden" name="doctor_id" value="<%=doctor_id%>" />
                <input type="hidden" name="doctor_name" value="<%=doctor_name%>" />
                <input type="hidden" name="patient_id" value="<%=patient_id.get(i)%>" />
                <input type="hidden" name="patient_name" value="<%=patient_name.get(i)%>" />
                <input type="hidden" name="message" value="<%=message.get(i)%>" />
                <input type="hidden" name="medicine" value="" />
                <input type="hidden" name="dose" value="0" />
                <input type="hidden" name="quantity" value="0" />
                <input type="hidden" name="opn" value="doc" />
                <td><%=patient_id.get(i)%></td>
                <td><%=patient_name.get(i)%></td>
                <td><%=message.get(i)%></td>
                <td><input type="submit" value="Select"></a></td>
            </form>
        </tr>
        <%}%>
    </table>
</c:if>
    
<c:if test="${sessionScope.count <= 0}"> 
    <h2 class="blink-one" style="color: blue; font-style: italic;">There are no new appointments</h2>
</c:if>
    
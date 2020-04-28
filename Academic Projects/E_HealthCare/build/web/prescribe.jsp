<%-- 
    Document   : prescribe
    Created on : Mar 31, 2020, 1:46:57 PM
    Author     : prite
--%>

<%@page import="com.neu.model.PatientAttend"%>
<%@page import="com.neu.controller.PrescribeController"%>
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
<script>
    
function getValue() {

    var x = document.getElementById("dose").value;
    document.getElementById("dose").setAttribute("value", x);
    
    x = document.getElementById("quantity").value;
    document.getElementById("quantity").setAttribute("value", x);
    
}
    
</script>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance"  user="root"  password="password"/>  
<% 
    PatientAttend pc = new PatientAttend();
    pc = (PatientAttend)request.getAttribute("patient");
    String p_id = pc.getPatient_id();
    String message = pc.getMessage();
    String p_name = pc.getPatient_name();
    int d_id = pc.getDoctor_id();
    String d_name = pc.getDoctor_name();
    String medicine = pc.getMedicine();
    int dose = pc.getDose();
    int quantity = pc.getQuantity();
%>           
<div class="header">
    <h1 style="margin-left: 150px;">Prescribe medicines to <%= p_name %></h1>
</div>

<table border="2" width="100%" style="text-align: center;">
    
    <tr>
        <th>Medicine Name</th>
        <th>Dose (per day)</th>
        <th>Quantity (no. of packets)</th>
        <th>Action</th>
    </tr>
    <c:forEach begin="1" end= "1" step="1" >
        <tr>
            <form action="PrescribeController" method="post">
                <input type="hidden" name="patient_id" value= <%= p_id%> />
                <input type="hidden" name="patient_name" value= <%= p_name%> />
                <input type="hidden" name="doctor_id" value= <%= d_id%> />
                <input type="hidden" name="doctor_name" value= <%= d_name%> />
                <input type="hidden" name="message" value= <%= message%> />
                <input type="hidden" name="opn" value= "add" />
                <td><input type="text" name="medicine" id="medicine" style="width: 50%;"/></td>
                <td><input type="number" name="dose" id="dose" style="width: 20%;"/></td>
                <td><input type="number" name="quantity" value="" id="quantity" style="width: 20%;"/></td>
                <td><input type="submit" value="Add"/></td>
            </form>
        </tr>
    </c:forEach>
</table>

<sql:query dataSource="${db}" var="ps">  
    select * from medicine where patient_id = <%=p_id%>;
</sql:query> 
    
<br><br>

<sql:query dataSource="${db}" var="count">  
    select count(*) as count from medicine where patient_id = <%=p_id%>;
</sql:query>
    
<c:forEach var="table" items="${count.rows}">
    <c:set var = "count" scope = "session" value = "${table.count}"/>
</c:forEach>
<hr>
<c:if test="${sessionScope.count > 0}"> 
    <table border="2" width="100%" style="text-align: center;">
        <h2>Medicines prescribed</h2>
        <tr>
            <th>Medicine Name</th>
            <th>Dose (per day)</th>
            <th>Quantity (no. of packets)</th>
            <th>Action</th>
        </tr>
        <c:forEach var="table" items="${ps.rows}">

            <tr>
                <form action="PrescribeController" method="post">
                    <input type="hidden" name="patient_id" value= <%= p_id%> />
                    <input type="hidden" name="patient_name" value= <%= p_name%> />
                    <input type="hidden" name="doctor_id" value= <%= d_id%> />
                    <input type="hidden" name="doctor_name" value= <%= d_name%> />
                    <input type="hidden" name="message" value= <%= message%> />
                    <input type="hidden" name="opn" value= "delete" />

                    <td><input type="text" name="medicine" id="medicine" value="${table.medicine}" style="width: 50%;" readonly="true"/></td>
                    <td><input type="text" name="dose" id="dose" value="${table.dose}" style="width: 20%;" readonly="true"/></td>
                    <td><input type="text" name="quantity" id="quantity" value="${table.quantity}"  style="width: 20%;" readonly="true"/></td>
                    <td><input type="submit" value="Delete"/></td>
                </form>
            </tr>

        </c:forEach>
    </table>
    
    <div class="container">
        <form action="EmailSendingServlet" method="post">
            <input type="hidden" name="patient_name" value= <%=p_name%> />
            <input type="hidden" name="doctor_name" value= <%=d_name%> />
            <input type="hidden" name="doctor_id" value= <%=d_id%> />
            <input type="submit" value="Prescribe"/>
        </form>
    </div>
</c:if>
    
<c:if test="${sessionScope.count == 0}">
    <div class="container">
        <form action="NoEmailSendingServlet" method="post">
            <input type="hidden" name="patient_name" value= <%= p_name%> />
            <input type="hidden" name="doctor_name" value= <%= d_name%> />
            <input type="hidden" name="doctor_id" value= <%=d_id%> />
            <input type="submit" value="Prescribe"/>
        </form>
    </div>
</c:if> 

<br><br>

<%@include file = "footer.html"%>  
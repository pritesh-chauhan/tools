<%-- 
    Document   : re_patient
    Created on : Mar 27, 2020, 11:57:52 PM
    Author     : prite
--%>

<%@page import="com.neu.model.RePatientDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<script language="JavaScript" type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>  
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
</form>
</div>
<hr><br><br>
<script>
    function myButtonDisable(count) {
        console.log(typeof(${count}));
        if( count >= 3){
            document.getElementById("proceed").disabled = true;
            document.getElementById("proceed").setAttribute("style","background-color: black;");
            document.getElementById("alert").innerHTML = "ALERT: There are no appointments available for assigned doctor.";
         }
    }
    function myCheckFunction() {
        alert(${sessionScope.count_illness}+" ----- "+${sessionScope.count_medicine});
        document.getElementById("proceed").setAttribute("disabled", "true");
        document.getElementById("proceed").setAttribute("style","background-color: black;");
        document.getElementById("change").setAttribute("disabled", "true");
        document.getElementById("change").setAttribute("style","background-color: black;");
        if(${sessionScope.count_illness} > 0 && ${sessionScope.count_medicine} > 0){
            document.getElementById("med").setAttribute("hidden", "true");
            document.getElementById("doc").setAttribute("hidden", "false");
        }
        else if(${sessionScope.count_medicine} > 0){
            document.getElementById("doc").setAttribute("hidden", "true");
            document.getElementById("med").setAttribute("hidden", "false");
        }
    }
</script>
<div class="container">
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance"  user="root"  password="password"/>  
    
    <%        
        RePatientDetails rep = new RePatientDetails();
        rep = (RePatientDetails)request.getAttribute("re_patient");
        
        String patient_name = rep.getPatient_name();
        int patient_id = rep.getPatient_id();
        String doctor_name = rep.getDoctor_name();
        int doctor_id = rep.getDoctor_id();
        int doctor_count = rep.getDoctor_count();
        int count_illness = rep.getCount_illness();
        int count_medicine = rep.getCount_medicine();
    %>
    
    <c:set var = "patient_name" scope = "session" value = "<%=patient_name%>"/>
    <c:set var = "count_illness" scope = "session" value = "<%=count_illness%>"/>
    <c:set var = "count_medicine" scope = "session" value = "<%=count_medicine%>"/>
    <c:set var = "doctor_name" scope = "session" value = "<%=doctor_name%>"/>
    
    
    <h1>Welcome <%= patient_name%> again to the E_Healthcare </h1>
    <hr>
    <c:if test="${(sessionScope.count_illness == 0) and (sessionScope.count_medicine == 0) and (sessionScope.patient_name != null)}">
        
        <h3>Currently, enrolled appointed <%=doctor_name%> doctor.</h3>
        
        <form action="RevisitController" method="post">
            <%
                session.setAttribute("fullname", patient_name);
                session.setAttribute("id", patient_id);
            %>
            <input type="hidden" name="patient_id" value= <%=patient_id%> />
            <input type="hidden" name="patient_name" value= <%=patient_name%> />
            <input type="hidden" name="doctor_id" value= <%=doctor_id%> /> 
            <input type="hidden" name="doctor_name" value= <%=doctor_name%> />
            <input type="hidden" name="doctor_count" value="<%= doctor_count %>" readonly="true" />
            <input type="submit" id="change" value="Change Doctor"></input> &nbsp;&nbsp;&nbsp;&nbsp;
        </form>
            
        <form id="revisit" action="RevisitControllerServlet" method="post">
            <input type="hidden" name="patient_id" value= <%=patient_id%> />
            <input type="hidden" name="patient_name" value= <%=patient_name%> />
            <input type="hidden" name="doctor_id" value= <%=doctor_id%> /> 
            <input type="hidden" name="doctor_name" value= <%=doctor_name%> />
            <input type="hidden" name="doctor_count" value="<%= doctor_count %>" readonly="true" />
            
            <c:if test="${not empty sessionScope.doctor_name}">
                <input type="submit" id= "proceed" onclick="myButtonDisable(<%=doctor_count%>)" value="Proceed with current doctor"/>
            </c:if>
            <c:if test="${empty sessionScope.doctor_name}">
                <input type="submit" id= "proceed" disabled="" style="background-color: black" value="Proceed with current doctor"/>
            </c:if>
        </form>
    </c:if>
    <hr>
            
    <c:if test="${( sessionScope.count_illness > 0 )}">
        <h1 class="blink-one"><i>!!!!!!!!!Doctor has not yet prescribed medicines!!!!!!!!!</i></h1>
    </c:if>    
        
    <c:if test="${( sessionScope.count_illness == 0 ) && ( sessionScope.count_medicine > 0 )}">
        <h1 class="blink-one" id="med"><i>!!!!!!!!!Medicines prescribed but it not yet delivered!!!!!!!!!</i></h1>
    </c:if>    
    
    <c:if test="${( sessionScope.patient_name == null)}">
        <h1 class="blink-one"><i>!!!!!!!!!Please login again session has expired!!!!!!!!!</i></h1>
        <h2>Please select required option</h2>
        <a href="register.jsp">Register</a>&nbsp;&nbsp;
        <a href="login.jsp">Login</a>
    </c:if>    


    <br><br>
</div>

<%@include file = "footer.html"%>  

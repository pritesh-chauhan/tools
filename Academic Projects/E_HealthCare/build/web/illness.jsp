<%-- 

    Document   : illness
    Created on : Mar 31, 2020, 4:50:08 PM
    Author     : prite

    From this page the Patient can add the details of the illness which will be sent to the selected doctor.

--%>

<%@page import="com.neu.model.DoctorSelect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
</form>

<hr><br><br>

<script>
function getMessage() {
  var x = document.getElementById("message").value;
  document.getElementById("message").setAttribute("value", x);
  document.getElementById("demo").setAttribute("value", x);
  document.getElementById("demo").innerHTML = x;
}
</script>

<h1>Provide details of the visit</h1>  

<% 
    DoctorSelect ds = new DoctorSelect();
    ds = (DoctorSelect)request.getAttribute("doctor_selection");

    String p_name = ds.getPatient_name();
    int p_id = ds.getPatient_id();
    String d_name = ds.getDoctor_name();
    int d_id = ds.getDoctor_id();
%>


<h2>Send Message to <%= d_name %></h2>

<form action="msg.jsp" method="post">
    <textarea id="message" name="message"></textarea><br><br>
    <input type="hidden" id="demo" name="message" value="" />
    <input type="hidden" name="patient_id" value= <%= p_id%> />
    <input type="hidden" name="patient_name" value= <%= p_name%> />
    <input type="hidden" name="doctor_id" value= <%= d_id%> />
    <input type="hidden" name="doctor_name" value= <%= d_name%> />
    <input type="submit" value="Send" onclick="getMessage()" style="margin-left: 720px;"/>
</form>

<%@include file = "footer.html"%>  
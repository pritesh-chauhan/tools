<%-- 
    Document   : pharmacist
    Created on : Mar 26, 2020, 11:25:00 PM
    Author     : prite
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@include file = "header.html"%> 
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
</form>

<hr><br><br>    

<div class="container">
    <h1>New Orders</h1>
</div>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance"  user="root"  password="password"/>  


<sql:query dataSource="${db}" var="ps">  
    select * from medicine;
</sql:query>
    
<sql:query dataSource="${db}" var="count">  
    select count(*) as count from medicine;
</sql:query>    

<c:forEach var="table" items="${count.rows}">
    <c:set var = "count" scope = "session" value = "${table.count}"/>
</c:forEach>
<c:if test="${sessionScope.count > 0}"> 
    <table border="2" width="100%" style="text-align: center;">  

        <th>Patient Name</th>
        <th>Doctor Name</th>
        <th>Medicine</th>
        <th>Quantity</th>
        <th>Action</th>

        <c:forEach var="table" items="${ps.rows}">
            <tr>  
                <form action="med_order.jsp" method="post">

                    <input type="hidden" name="patient_id" value="${table.patient_id}"/>
                    <td><input type="text" name="patient_name" value="${table.patient_name}" readonly/></td>
                    <input type="hidden" name="doctor_id" value="${table.doctor_id}"/>
                    <td><input type="text" name="doctor_name" value="${table.doctor_name}" readonly/></td>
                    <td><input type="text" name="medicine"  value="${table.medicine}" readonly/></td>
                    <input type="hidden" name="dose"  value="${table.dose}"/>
                    <td><input type="text" name="quantity" value="${table.quantity}" readonly/></td>
                    <td><input type="submit" value="Place Order"/></td>
                </form>
            </tr>  
        </c:forEach>
    </table>
</c:if>    
    
<c:if test="${sessionScope.count <= 0}"> 
    <h2 class="blink-one" style="color: blue; font-style: italic;">No new medicines to add to order</h2>
</c:if>    
    
<hr>
    
<div class="container">
    <h1>Medicines out of Stock</h1>
</div>  
    
<sql:query dataSource="${db}" var="ps">  
    select medicine, sum(quantity) as quantity from orders group by medicine;
</sql:query>
    
    
<sql:query dataSource="${db}" var="rs">  
    select count(*) as count from orders;
</sql:query>
    
    
<c:forEach var="table" items="${rs.rows}">
    <c:set var = "count" scope = "session" value = "${table.count}"/>
</c:forEach>
    
<c:if test="${sessionScope.count > 0}">
    <table border="2" width="100%" style="text-align: center;">  
        <th>Medicine</th>
        <th>Quantity</th>
        <th>Action</th>
        <c:forEach var="table" items="${ps.rows}">
                <tr>  
                    <form action="pharma_add.jsp" method="post"> 
                        <td><input type="text" name="medicine"  value="${table.medicine}" readonly/></td>
                        <td><input type="text" name="quantity" value="${table.quantity}" readonly/></td>
                        <td><input type="submit" value="Add to Stock"/></td>
                    </form>
                </tr>  
        </c:forEach>
    </table>
</c:if>

<c:if test="${sessionScope.count <= 0}">
    <h2 class="blink-one" style="color: blue; font-style: italic;">No new medicines to add to stock</h2>
</c:if>

<%@include file = "footer.html"%>  
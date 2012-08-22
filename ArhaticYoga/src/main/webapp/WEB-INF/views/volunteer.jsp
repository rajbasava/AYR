<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>

<html>
<head>
	<title>Arhatic Yoga Retreat - Volunteer</title>
</head>
<mytags:style/>
</head>
<body>
<mytags:menu/>
<h2 align="center">Volunteer Manager</h2>

<form:form method="post" action="addVolunteer.htm" commandName="volunteer">

<table align="center" cellspacing="2">
    <tr>
		<td><form:label path="name"><spring:message code="label.name"/></form:label></td>
		<td><form:input path="name" size="50"/></td>
	</tr>
	<tr>
		<td><form:label path="email"><spring:message code="label.email"/></form:label></td>
		<td><form:input path="email"/></td>
	</tr>
	<tr>
		<td><form:label path="mobile"><spring:message code="label.mobile"/></form:label></td>
		<td><form:input path="mobile"/></td>
	</tr>
	<tr>
		<td><form:label path="password"><spring:message code="label.password"/></form:label></td>
		<td><form:password path="password"/></td>
	</tr>
	<tr>
		<td><form:label path="activity"><spring:message code="label.activity"/></form:label></td>
        <td><form:input path="activity" /></td>
	</tr>
    <tr>
        <td><form:label path="permission"><spring:message code="label.permission"/></form:label></td>
        <td><form:input path="permission"/></td>
    </tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="<spring:message code="label.addVolunteer"/>"/>
		</td>
	</tr>
</table>
</form:form>

<c:if  test="${!empty volunteerList}">
<h3>Volunteers</h3>
<table class="data" border="1" cellpadding="1" cellspacing="1" width="100%">
<tr>
    <th><spring:message code="label.name"/></th>
    <th><spring:message code="label.email"/></th>
    <th><spring:message code="label.mobile"/></th>
    <th><spring:message code="label.activity"/></th>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${volunteerList}" var="volunteer">
	<tr>
		<td><c:out value="${volunteer.name}"/> </td>
		<td><c:out value="${volunteer.email}"/></td>
		<td><c:out value="${volunteer.mobile}"/></td>
		<td><c:out value="${volunteer.activity}"/></td>
		<td>
            <form id="delVol" method="post" action="delete.htm">
            <input type="hidden" name="volunteerId" value="<c:out value="${volunteer.volunteerId}"/>" />      
            <a href="#" onclick="document.getElementById('delVol').submit();"><spring:message code="label.delete"/></a>
            </form>    
        </td>
	</tr>
</c:forEach>
</table>
</c:if>

<mytags:footer/>
</body>
</html>

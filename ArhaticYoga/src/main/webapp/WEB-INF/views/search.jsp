<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>

<html>
<head>
	<title>Arhatic Yoga Retreat - Search</title>
</head>
<mytags:style/>
</head>
<body>
<mytags:menu/>
<h2 align="center">Search Participants</h2>

<form:form method="post" action="list.htm" commandName="participantCriteria">

<table align="center" cellspacing="2">
    <tr>
		<td><form:label path="name"><spring:message code="label.name"/></form:label></td>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<td><form:label path="email"><spring:message code="label.email"/></form:label></td>
		<td><form:input path="email" /></td>
	</tr>
	<tr>
		<td><form:label path="mobile"><spring:message code="label.mobile"/></form:label></td>
		<td><form:input path="mobile" /></td>
	</tr>
	<tr>
		<td><form:label path="foundation"><spring:message code="label.foundation"/></form:label></td>
		<td><form:input path="foundation" size="50"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="label.search"/>"/>
		</td>
	</tr>
</table>
</form:form>

<h3>Participants</h3>
<c:if  test="${!empty participantList}">
<table class="data" border="1" cellpadding="1" cellspacing="1" width="100%">
<tr>
	<th><spring:message code="label.name"/></th>
	<th><spring:message code="label.email"/></th>
	<th><spring:message code="label.mobile"/></th>
	<th><spring:message code="label.foundation"/></th>
	<th><spring:message code="label.level"/></th>
	<th><spring:message code="label.foodcoupon"/></th>
	<th><spring:message code="label.eventkit"/></th>
	<th><spring:message code="label.amountpaid"/></th>
	<th><spring:message code="label.dueamount"/></th>
	<th><spring:message code="label.comments"/></th>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${participantList}" var="participant">
	<tr>
		<td><c:out value="${participant.name}"/> </td>
		<td><c:out value="${participant.email}"/></td>
		<td><c:out value="${participant.mobile}"/></td>
		<td><c:out value="${participant.foundation}"/></td>
		<td><c:out value="${participant.level}"/></td>
		<td><c:out value="${participant.foodcoupon}"/></td>
		<td><c:out value="${participant.eventkit}"/></td>
		<td><c:out value="${participant.amountpaid}"/></td>
		<td><c:out value="${participant.dueamount}"/></td>
		<td><a href="comments/<c:out value="${participant.participantid}"/>"><spring:message code="label.comments"/></a></td>
		<td><a href="update/<c:out value="${participant.participantid}"/>"><spring:message code="label.action"/></a></td>
	</tr>
</c:forEach>
</table>
</c:if>


<hr />
<mytags:footer/>
</body>
</html>

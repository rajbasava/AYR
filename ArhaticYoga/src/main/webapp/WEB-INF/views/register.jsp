<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="mytags"%>

<html>
<head>
	<title>Arhatic Yoga Retreat - Registration</title>
</head>
<mytags:style/>
</head>
<body>
<mytags:menu/>
<h2 align="center">User Registration</h2>

<form:form method="post" action="add.htm" commandName="registeredParticipant">

<table align="center" cellspacing="2">
    <tr>
		<td><form:label path="participant.name"><spring:message code="label.name"/></form:label></td>
		<td><form:input path="participant.name" size="50"/></td>
	</tr>
	<tr>
		<td><form:label path="participant.email"><spring:message code="label.email"/></form:label></td>
		<td><form:input path="participant.email" /></td>
	</tr>
	<tr>
		<td><form:label path="participant.mobile"><spring:message code="label.mobile"/></form:label></td>
		<td><form:input path="participant.mobile" /></td>
	</tr>
	<tr>
		<td><form:label path="participant.foundation"><spring:message code="label.foundation"/></form:label></td>
		<td><form:input path="participant.foundation" size="50"/></td>
	</tr>
	<tr>
		<td><form:label path="participant.level"><spring:message code="label.level"/></form:label></td>
		<td>
            <form:radiobutton path="participant.level" value="PH1" label="Basic Pranic Healing"/>
            <form:radiobutton path="participant.level" value="PH2" label="Advanced Pranic Healing"/>
            <form:radiobutton path="participant.level" value="PH3" label="Pranic Physotherapy"/>
            <form:radiobutton path="participant.level" value="AYL0" label="Arahtic Yoga Preparation"/>
            <form:radiobutton path="participant.level" value="AYL1" label="Arahtic Yoga Level 1"/>
            <form:radiobutton path="participant.level" value="AYL2" label="Arahtic Yoga Level 2"/>
            <form:radiobutton path="participant.level" value="AYL3.1" label="Arahtic Yoga Level 3.1"/>
            <form:radiobutton path="participant.level" value="AYL3.2" label="Arahtic Yoga Level 3.2"/>
            <form:radiobutton path="participant.level" value="AYL4" label="Arahtic Yoga Level 4"/>
        </td>
	</tr>
    <tr>
        <td><form:label path="participant.foodcoupon"><spring:message code="label.foodcoupon"/></form:label></td>
        <td><form:checkbox path="participant.foodcoupon"/></td>
    </tr>
    <tr>
        <td><form:label path="participant.eventkit"><spring:message code="label.eventkit"/></form:label></td>
        <td><form:checkbox path="participant.eventkit"/></td>
    </tr>
    <tr>
        <td><form:label path="participant.amountpaid"><spring:message code="label.amountpaid"/></form:label></td>
        <td><form:input path="participant.amountpaid"/></td>
    </tr>
    <tr>
        <td><form:label path="participant.dueamount"><spring:message code="label.dueamount"/></form:label></td>
        <td><form:input path="participant.dueamount"/></td>
    </tr>
    <tr>
        <td><form:label path="comments[0].comments"><spring:message code="label.comments"/></form:label></td>
        <td><form:textarea path="comments[0].comments" rows="5" cols="30"/></td>
    </tr>
	<tr>
		<td colspan="2" align="center">
            <form:hidden path="action"/>
            <form:hidden path="participant.participantId"/>
			<input type="submit" value="<c:out value="${registeredParticipant.action}"/>"/>
		</td>
	</tr>
    <c:if  test="${!empty registeredParticipant.comments}">
        <tr>
            <td colspan="2">
                <h4>Comments:</h4>
            </td>
        </tr>
        <tr>
            <table class="data" border="1" cellpadding="1" cellspacing="1" width="100%">
            <tr>
                <th>Prepared By</th>
                <th>Time Created</th>
                <th>Comment</th>
            </tr>
            <c:forEach items="${registeredParticipant.comments}" var="comment">
                <c:if  test="${comment.comments != null}">
                <tr>
                    <td><c:out value="${comment.preparedby}"/> </td>
                    <td><c:out value="${comment.timecreated}"/></td>
                    <td><c:out value="${comment.comments}"/></td>
                </tr>
                </c:if>
            </c:forEach>
            </table>
        </tr>
    </c:if>
</table>
</form:form>
<mytags:footer/>
</body>
</html>

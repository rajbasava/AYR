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
<h2 align="center">User Registration Summary</h2>

<table align="center" cellspacing="2" cellpadding="2" width="50%">
    <tr>
		<td><spring:message code="label.name"/>:</td>
		<td><c:out value="${registeredParticipant.participant.name}"/></td>
	</tr>
	<tr>
		<td><spring:message code="label.email"/>:</td>
		<td><c:out value="${registeredParticipant.participant.email}"/></td>
	</tr>
	<tr>
		<td><spring:message code="label.mobile"/>:</td>
		<td><c:out value="${registeredParticipant.participant.mobile}"/></td>
	</tr>
	<tr>
		<td><spring:message code="label.foundation"/>:</td>
		<td><c:out value="${registeredParticipant.participant.foundation}"/></td>
	</tr>
	<tr>
		<td><spring:message code="label.level"/>:</td>
		<td>
            <c:out value="${registeredParticipant.participant.levelName}"/>
        </td>
	</tr>
    <tr>
        <td><spring:message code="label.foodcoupon"/></td>
        <td><c:out value="${registeredParticipant.participant.foodcoupon}"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.eventkit"/></td>
        <td><c:out value="${registeredParticipant.participant.eventkit}"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.amountpaid"/></td>
        <td><c:out value="${registeredParticipant.participant.amountpaid}"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.dueamount"/></td>
        <td><c:out value="${registeredParticipant.participant.dueamount}"/></td>
    </tr>
    </table>
    <table cellspacing="1" cellpadding="1" width="100%">
    <c:if  test="${!empty registeredParticipant.seats}">
        <tr align="left">
            <td>
                <b>Seats:</b>
            </td>
        </tr>
        <tr>
            <table class="data" border="1" cellpadding="1" cellspacing="1" width="100%">
            <tr>
                <td>Level</td>
                <td>Seat No</td>
            </tr>
            <c:forEach items="${registeredParticipant.seats}" var="seat">
                <c:if  test="${seat.level != null}">
                <tr>
                    <td><c:out value="${seat.level}"/> </td>
                    <td><c:out value="${seat.seat}"/></td>
                </tr>
                </c:if>
            </c:forEach>
            </table>
        </tr>
    </c:if>
    <tr><td>&nbsp;<BR></td></tr>
    <c:if  test="${!empty registeredParticipant.comments}">
        <tr align="left">
            <td>
                <b>Comments:</b>
            </td>
        </tr>
        <tr>
            <table class="data" border="1" cellpadding="1" cellspacing="1" width="100%">
            <tr>
                <td>Prepared By</td>
                <td>Time Created</td>
                <td>Comment</td>
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
    <tr><td>&nbsp;<BR></td></tr>
	<tr align="center">
		<td align="center">
            <form method="post" action="register.htm">
			    <input type="submit" value="<spring:message code="label.nextRegistration"/>"/>
            </form>
		</td>
	</tr>
</table>
<mytags:footer/>
</body>
</html>

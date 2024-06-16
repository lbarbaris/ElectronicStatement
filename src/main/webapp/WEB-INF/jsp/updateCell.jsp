<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Cell</title>
</head>
<body>
<h2>Update Cell</h2>

<form:form action="saveCell" modelAttribute="oneCell" method="post">
    <input type="hidden" name="sheetId" value="${sheet.id}">
    <form:select path="id">
        <c:forEach var="cell" items="${cells}">
            <form:option value="${cell.id}" label="${cell.value}"/>
        </c:forEach>
    </form:select>

    <form:input path="value" placeholder="Enter new value"/>

    <input type="submit" name="action" value="update">
</form:form>


</body>
</html>

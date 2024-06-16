<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Row</title>
</head>
<body>
<h2>Update Row</h2>

<form:form action="saveColumn" modelAttribute="oneColumn" method="post">
    <input type="hidden" name="sheetId" value="${sheet.id}">
    <!-- Dropdown list for selecting a row -->
    <form:select path="id">
        <c:forEach var="column" items="${cols}">
            <form:option value="${column.id}" label="${column.name}"/>
        </c:forEach>
    </form:select>

    <form:input path="name" placeholder="Enter new name"/>

    <input type="submit" name="action" value="update">
    <input type="submit" name="action" value="delete">
</form:form>


</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${oneRow.sheets}</title>
</head>
<body>
<h2>Update Row</h2>

<form:form action="saveRow" modelAttribute="oneRow" method="post">
    <input type="hidden" name="sheetId" value="${sheet.id}">
    <form:input path="name" placeholder="Enter new name"/>

    <input type="submit" name="action" value="add">
</form:form>


</body>
</html>

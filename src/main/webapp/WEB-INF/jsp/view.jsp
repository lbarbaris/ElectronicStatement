<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Таблица с именами строк и столбцов</title>
</head>
<body>
<c:forEach var="sheet" items="${sheets}">
<table border="1">
    <thead>
    <tr>
        <th>${sheet.name}</th>
        <c:forEach var="columnName" items="${statementColumns}">
            <c:if test="${columnName.sheets eq sheet}">
                <th>${columnName.name}</th>
            </c:if>

        </c:forEach>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="rowName" items="${statementRows}">
        <c:if test="${rowName.sheets eq sheet}">


        <tr>
            <td>${rowName.name}</td>
            <c:forEach var="columnName" items="${statementColumns}">
                <c:forEach var="cell" items="${cellValues}">
                    <c:if test="${cell.statementRows eq rowName and cell.statementColumns eq columnName}">
                        <td>${cell.value}</td>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </tr>
        </c:if>
    </c:forEach>
    <tr>
        <td>
            <c:url var="updateButton" value="/updateRow">
                <c:param name="sheetId" value="${sheet.id}"/>
            </c:url>
            <input type="button" value="Update"
                   onclick="window.location.href = '${updateButton}'"/>
        </td>
    </tr>
    </tbody>
</table>
    <br><br>
</c:forEach>
</body>
</html>

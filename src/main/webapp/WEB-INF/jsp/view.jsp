<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Таблица с именами строк и столбцов</title>
</head>
<body>
<sec:authorize access="hasAnyRole('ROLE_TEACHER')">
<c:url var="addSheet" value="/addSheet">
</c:url>
<input type="button" value="Add Sheet"
       onclick="window.location.href = '${addSheet}'"/>

<br>
<br>
<br>
</sec:authorize>
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
        <sec:authorize access="hasAnyRole('ROLE_TEACHER')">
        <th>

            <c:url var="updateButtonColumn" value="/updateColumn">
                <c:param name="sheetId" value="${sheet.id}"/>
            </c:url>
            <input type="button" value="Update"
                   onclick="window.location.href = '${updateButtonColumn}'"/>
            <c:url var="addButtonColumn" value="/addColumn">
                <c:param name="sheetId" value="${sheet.id}"/>
            </c:url>
            <input type="button" value="Add"
                   onclick="window.location.href = '${addButtonColumn}'"/>

        </th>
        </sec:authorize>
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
            <sec:authorize access="hasAnyRole('ROLE_TEACHER')">
            <td>
            <c:url var="updateButtonCell" value="/updateCell">
                <c:param name="sheetId" value="${sheet.id}"/>
                <c:param name="rowId" value="${rowName.id}"/>
            </c:url>

            <input type="button" value="Update"
                   onclick="window.location.href = '${updateButtonCell}'"/>
            </td>
            </sec:authorize>
        </tr>
        </c:if>
    </c:forEach>
    <tr>
        <sec:authorize access="hasAnyRole('ROLE_TEACHER')">
        <td>
            <c:url var="updateButtonRow" value="/updateRow">
                <c:param name="sheetId" value="${sheet.id}"/>
            </c:url>
            <input type="button" value="Update"
                   onclick="window.location.href = '${updateButtonRow}'"/>
            <c:url var="addButtonRow" value="/addRow">
                <c:param name="sheetId" value="${sheet.id}"/>
            </c:url>
            <input type="button" value="Add"
                   onclick="window.location.href = '${addButtonRow}'"/>
        </td>
        </sec:authorize>
    </tr>
    </tbody>
</table>
    <br><br>
</c:forEach>
</body>
</html>

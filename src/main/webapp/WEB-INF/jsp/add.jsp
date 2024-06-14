<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: lexa_barbaris
  Date: 02.06.2024
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<h2>Добро пожаловать в меню таблицы</h2>
<p>Пожалуйста, заполните форму</p>


<form:form action="save" modelAttribute="sheet">
    <form:hidden path="id"/>
    <c:forEach var="sheet" items="${sheets}">
    Название предмета <br><form:input path="sheet.name"/><br><br>
    Задание <br><form:input path="surname"/><br><br>
    ФИО студента <br><form:input path="name"/><br><br>
    Оценка <br><form:input path="department"/><br><br>
    <input type="submit" value="OK">
    </c:forEach>
</form:form>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<h2>Add Sheet</h2>

<form:form action="saveSheet" modelAttribute="oneSheet" method="post">
    Enter sheet name
    <br>
    <form:input path="name" placeholder="Enter sheet name"/>
    <br><br>
    Enter exercise
    <br>
    <input type="text" name="columnName" placeholder="Enter exercise name">
    <br><br>
    Enter student
    <br>
    <input type="text" name="rowName" placeholder="Enter student name">
    <br><br>
    <input type="submit" name="action" value="add">
</form:form>


</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>
<body>
<form:form action="processForm" modelAttribute="product">
    <h1>Welcome page</h1>
    Product ID: <form:input path="id" />
    <br>
    Product title: <form:input path="title" />
    <br>
	Product cost: <form:input path="cost" />
    <br>
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
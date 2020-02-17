<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>
<body>
<h1>All Product Form Result</h1>

<ul>
    <c:forEach var="item" items="${productContainer.products}">
        <li>Prodict id: ${item.id}</li>
		<li>Product title: ${item.title}</li>
		<li>Product cost: ${item.cost}</li>
		<br>
    </c:forEach>
</ul>
<br>
</body>
</html>
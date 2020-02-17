<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>
<body>
<h1>Product Form Result</h1>
<br>
Product ID: ${product.id}
<br>
Product title: ${product.title}
<br>
Product cost: ${product.cost}
<br>
</body>
</html>
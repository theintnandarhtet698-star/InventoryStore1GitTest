<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Form</title>
</head>
<body>
    <h2>Edit Product</h2>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${product.id}">
        
        Name: <input type="text" name="name" value="${product.name}" required><br><br>
        Price: <input type="number" step="0.01" name="price" value="${product.price}" required><br><br>
        Qty: <input type="number" name="quantity" value="${product.quantity}" required><br><br>
        
        <button type="submit">Update Product</button>
    </form>
    <br>
    <a href="list">Back to List</a>
</body>
</html>
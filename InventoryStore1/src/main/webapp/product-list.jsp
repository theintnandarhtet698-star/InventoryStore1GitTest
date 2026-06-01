<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inventory Store</title>
</head>
<body>

    <h3>Add Product</h3>
    <form action="insert" method="post">
        Name: <input type="text" name="name" required>
        Price: <input type="number" step="0.01" name="price" required>
        Qty: <input type="number" name="quantity" required>
        <button type="submit">Add</button>
    </form>

    <hr>

    <h3>Inventory List</h3>
    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${listProduct}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.quantity}</td>
                    <td>
                        <a href="edit?id=${p.id}">Edit</a> | 
                        <a href="delete?id=${p.id}" onclick="return confirm('Delete?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
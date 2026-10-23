<%@ page import="model.Item" %>

<%
    Item item = (Item) request.getAttribute("item");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Item</title>
</head>
<body>

    <h2>Update Item</h2>

    <form action="ItemController" method="post">
        <input type="hidden" name="action" value="updateItem">
        <input type="hidden" name="id" value="<%= item.getId() %>">

        <label>Name</label>
        <input type="text" name="name" value="<%= item.getName() %>" required>
        <br><br>

        <label>Price</label>
        <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required>
        <br><br>

        <label>Total Number</label>
        <input type="number" name="totalNumber" value="<%= item.getTotalNumber() %>" required>
        <br><br>

        <button type="submit">Update</button>
    </form>

</body>
</html>
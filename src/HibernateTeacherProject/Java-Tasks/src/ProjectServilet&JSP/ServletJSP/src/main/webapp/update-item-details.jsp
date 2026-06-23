<%@ page import="model.ItemDetails" %>

<%
    ItemDetails details = (ItemDetails) request.getAttribute("details");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Item Details</title>
</head>
<body>

    <h2>Update Item Details</h2>

    <form action="ItemDetailsController" method="post">
        <input type="hidden" name="action" value="updateItemDetails">
        <input type="hidden" name="itemId" value="<%= details.getItemId() %>">

        <label>Description</label>
        <input type="text" name="description" value="<%= details.getDescription() %>" required>
        <br><br>

        <label>Comments</label>
        <input type="text" name="comments" value="<%= details.getComments() %>" required>
        <br><br>

        <button type="submit">Update Details</button>
    </form>

</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item Details</title>
</head>
<body>

    <h2>Add Item Details</h2>

    <form action="ItemDetailsController" method="post">
        <input type="hidden" name="action" value="addItemDetails">
        <input type="hidden" name="itemId" value="${itemId}">

        <label>Description</label>
        <input type="text" name="description" required>
        <br><br>

        <label>Comments</label>
        <input type="text" name="comments" required>
        <br><br>

        <button type="submit">Save Details</button>
    </form>

</body>
</html>
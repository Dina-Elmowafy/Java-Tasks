<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>

<!DOCTYPE html>
<html>
<head>
    <title>Show Items</title>
</head>
<body>

    <h2>Items</h2>

    <a href="add-item.html">Add New Item</a>
    |
    <a href="AuthController?action=logout">Logout</a>
    |
    <a href="AuthController?action=deleteAccount">Delete Account</a>

    <br><br>

    <%
        List<Item> items = (List<Item>) request.getAttribute("items");

        if (items == null || items.isEmpty()) {
    %>

        <h3>No item found</h3>

    <%
        } else {
    %>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Total Number</th>
                <th>Actions</th>
            </tr>

            <%
                for (Item item : items) {
            %>

            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getTotalNumber() %></td>
                <td>
                    <a href="ItemController?action=showUpdateItem&id=<%= item.getId() %>">Update Item</a>
                    |
                    <a href="ItemController?action=deleteItem&id=<%= item.getId() %>">Delete Item</a>
                    |

                    <%
                        if (!item.isHasDetails()) {
                    %>
                        <a href="ItemDetailsController?action=showAddDetails&itemId=<%= item.getId() %>">
                            Add Item Details
                        </a>
                    <%
                        } else {
                    %>
                        <a href="ItemDetailsController?action=showUpdateDetails&itemId=<%= item.getId() %>">
                            Update Item Details
                        </a>
                    <%
                        }
                    %>
                </td>
            </tr>

            <%
                }
            %>

        </table>

    <%
        }
    %>

</body>
</html>
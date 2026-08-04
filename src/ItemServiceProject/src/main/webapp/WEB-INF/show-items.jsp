<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.item.model.Item" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Items</title>

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            min-height: 100vh;
            padding: 50px 20px;
            font-family: Arial, sans-serif;
            color: #1e293b;

            background:
                radial-gradient(
                    circle at top left,
                    #c7d2fe,
                    transparent 35%
                ),
                linear-gradient(
                    135deg,
                    #f8fafc,
                    #eef2ff
                );
        }

        .container {
            width: 100%;
            max-width: 1050px;
            margin: auto;
        }

        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 20px;
            margin-bottom: 25px;
        }

        .title h1 {
            margin-bottom: 7px;
            color: #312e81;
            font-size: 34px;
        }

        .title p {
            color: #64748b;
        }

        .add-button {
            display: inline-block;
            padding: 12px 20px;
            border-radius: 10px;

            color: white;
            background:
                linear-gradient(
                    135deg,
                    #4f46e5,
                    #7c3aed
                );

            text-decoration: none;
            font-weight: bold;

            box-shadow:
                0 8px 20px rgba(79, 70, 229, 0.25);

            transition: 0.2s;
        }

        .add-button:hover {
            transform: translateY(-2px);

            box-shadow:
                0 12px 25px rgba(79, 70, 229, 0.35);
        }

        .table-container {
            overflow: hidden;

            border:
                1px solid #e2e8f0;

            border-radius: 18px;
            background: white;

            box-shadow:
                0 18px 45px rgba(30, 41, 59, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            color: white;

            background:
                linear-gradient(
                    90deg,
                    #4338ca,
                    #7c3aed
                );
        }

        th {
            padding: 18px 20px;

            font-size: 13px;
            text-align: left;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        td {
            padding: 17px 20px;
        }

        tbody tr {
            border-bottom:
                1px solid #e2e8f0;

            transition: 0.2s;
        }

        tbody tr:last-child {
            border-bottom: none;
        }

        tbody tr:nth-child(even) {
            background: #f8fafc;
        }

        tbody tr:hover {
            background: #eef2ff;
        }

        .id {
            color: #6366f1;
            font-weight: bold;
        }

        .name {
            color: #0f172a;
            font-weight: bold;
        }

        .price {
            color: #059669;
            font-weight: bold;
        }

        .total-number {
            display: inline-block;

            min-width: 45px;
            padding: 6px 12px;
            border-radius: 20px;

            color: #4338ca;
            background: #e0e7ff;

            font-weight: bold;
            text-align: center;
        }

        .actions {
            display: flex;
            gap: 9px;
        }

        .button {
            display: inline-block;

            padding: 8px 14px;
            border-radius: 8px;

            text-decoration: none;
            font-size: 12px;
            font-weight: bold;
            text-transform: uppercase;

            transition: 0.2s;
        }

        .update-button {
            color: #166534;
            background: #dcfce7;
        }

        .update-button:hover {
            color: white;
            background: #16a34a;
        }

        .delete-button {
            color: #b91c1c;
            background: #fee2e2;
        }

        .delete-button:hover {
            color: white;
            background: #dc2626;
        }

        .empty-message {
            padding: 60px 20px;
            color: #64748b;
            text-align: center;
        }

        .empty-icon {
            display: block;
            margin-bottom: 15px;
            font-size: 45px;
        }

        .empty-message h2 {
            margin-bottom: 7px;
            color: #312e81;
        }

        @media (max-width: 750px) {

            .header {
                align-items: flex-start;
                flex-direction: column;
            }

            .table-container {
                overflow-x: auto;
            }

            table {
                min-width: 750px;
            }
        }
    </style>
</head>

<body>

<%
    List<Item> items =
        (List<Item>) request.getAttribute("getAllItem");
%>

<div class="container">

    <div class="header">

        <div class="title">
            <h1>Items</h1>

            <p>
                Manage all items in your database
            </p>
        </div>

        <a class="add-button"
           href="<%= request.getContextPath() %>/Controler?action=add-item">

            + Add Item
        </a>

    </div>

    <div class="table-container">

        <% if (items != null && !items.isEmpty()) { %>

            <table>

                <thead>
                    <tr>
                        <th>ID</th>

                        <th>Name</th>

                        <th>Price</th>

                        <th>Total Number</th>

                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>

                <% for (Item currentItem : items) { %>

                    <tr>

                        <td class="id">
                            #<%= currentItem.getId() %>
                        </td>

                        <td class="name">
                            <%= currentItem.getName() %>
                        </td>

                        <td class="price">
                            <%= currentItem.getPrice() %>
                        </td>

                        <td>
                            <span class="total-number">
                                <%= currentItem.getTotalNumber() %>
                            </span>
                        </td>

                        <td>

                            <div class="actions">

                                <a
                                    class="button update-button"

                                    href="<%= request.getContextPath() %>/Controler?action=update-item&id=<%= currentItem.getId() %>">

                                    Update
                                </a>

                                <a
                                    class="button delete-button"

                                    href="<%= request.getContextPath() %>/Controler?action=delete-item&id=<%= currentItem.getId() %>"

                                    onclick="return confirm('Are you sure you want to delete this item?');">

                                    Delete
                                </a>

                            </div>

                        </td>

                    </tr>

                <% } %>

                </tbody>

            </table>

        <% } else { %>

            <div class="empty-message">

                <span class="empty-icon">
                    &#128230;
                </span>

                <h2>No items found</h2>

                <p>
                    There are no items in the database.
                </p>

            </div>

        <% } %>

    </div>

</div>

</body>
</html>
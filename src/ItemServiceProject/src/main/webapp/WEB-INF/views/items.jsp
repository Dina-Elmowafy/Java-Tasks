<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h1>Items</h1>

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
        <c:forEach items="${allItems}" var="item">
            <tr>
                <td>${item.id}</td>
                <td><c:out value="${item.name}"/></td>
                <td>${item.price}</td>
                <td>${item.totalNumber}</td>
                <td>
                    <a class="button update"
                       href="${pageContext.request.contextPath}/ItemController?action=update-item&id=${item.id}">
                        Update
                    </a>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty allItems}">
            <tr>
                <td colspan="5">No items found</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <a class="button add"
       href="${pageContext.request.contextPath}/ItemController?action=add-item">
        Add Item
    </a>
</div>
</body>
</html>

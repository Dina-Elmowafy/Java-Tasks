<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${formAction == 'update-item' ? 'Update Item' : 'Add Item'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="form-card">
    <h1>${formAction == 'update-item' ? 'Update Item' : 'Add Item'}</h1>

    <form action="${pageContext.request.contextPath}/ItemController" method="post">
        <input type="hidden" name="action" value="${formAction}">

        <c:if test="${formAction == 'update-item'}">
            <input type="hidden" name="id" value="${item.id}">
        </c:if>

        <label for="name">Name</label>
        <input id="name" name="name" type="text"
               value="<c:out value='${item.name}'/>" maxlength="100" required>

        <label for="price">Price</label>
        <input id="price" name="price" type="number"
               value="${item.price}" min="0" step="0.01" required>

        <label for="totalNumber">Total Number</label>
        <input id="totalNumber" name="totalNumber" type="number"
               value="${item.totalNumber}" min="0" required>

        <div class="form-actions">
            <button class="button save" type="submit">Save</button>
            <a class="button cancel"
               href="${pageContext.request.contextPath}/ItemController">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>

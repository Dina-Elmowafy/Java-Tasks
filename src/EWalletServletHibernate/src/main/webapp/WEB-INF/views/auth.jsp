<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student E-Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="auth-page">
<main class="auth-card">
    <h1>Student E-Wallet</h1>
    <p class="muted">A simple Servlet + Hibernate project</p>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert error"><%= request.getAttribute("error") %></div>
    <% } %>
    <div class="forms">
        <form method="post" action="${pageContext.request.contextPath}/auth">
            <h2>Login</h2>
            <input type="hidden" name="action" value="login">
            <label>Email<input type="email" name="email" required></label>
            <label>Password<input type="password" name="password" required></label>
            <button type="submit">Login</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/auth">
            <h2>Create account</h2>
            <input type="hidden" name="action" value="register">
            <label>Full name<input name="name" maxlength="80" required></label>
            <label>Email<input type="email" name="email" required></label>
            <label>Password<input type="password" name="password" minlength="6" required></label>
            <button type="submit">Register</button>
        </form>
    </div>
</main>
</body>
</html>

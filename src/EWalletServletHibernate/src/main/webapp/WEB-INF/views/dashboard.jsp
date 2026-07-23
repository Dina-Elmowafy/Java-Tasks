<%@ page import="com.student.ewallet.model.*,java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Wallet wallet = (Wallet) request.getAttribute("wallet");
    List<WalletTransaction> transactions =
            (List<WalletTransaction>) request.getAttribute("transactions");
    String message = (String) session.getAttribute("message");
    String error = (String) session.getAttribute("error");
    session.removeAttribute("message");
    session.removeAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Wallet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <div><strong>E-Wallet</strong><span>Hello, <%= wallet.getOwner().getFullName() %></span></div>
    <form method="post" action="${pageContext.request.contextPath}/logout">
        <button class="secondary">Logout</button>
    </form>
</header>
<main class="container">
    <% if (message != null) { %><div class="alert success"><%= message %></div><% } %>
    <% if (error != null) { %><div class="alert error"><%= error %></div><% } %>
    <section class="balance-card">
        <p>Available balance</p>
        <h1><%= wallet.getBalance() %> EGP</h1>
        <small>Wallet number: <strong><%= wallet.getWalletNumber() %></strong></small>
    </section>
    <section class="actions">
        <form method="post" action="${pageContext.request.contextPath}/wallet">
            <h3>Deposit</h3>
            <input type="hidden" name="action" value="deposit">
            <input type="number" name="amount" min="0.01" step="0.01" placeholder="Amount" required>
            <button>Deposit</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/wallet">
            <h3>Withdraw</h3>
            <input type="hidden" name="action" value="withdraw">
            <input type="number" name="amount" min="0.01" step="0.01" placeholder="Amount" required>
            <button>Withdraw</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/wallet">
            <h3>Transfer</h3>
            <input type="hidden" name="action" value="transfer">
            <input name="receiver" placeholder="Receiver wallet number" required>
            <input type="number" name="amount" min="0.01" step="0.01" placeholder="Amount" required>
            <button>Transfer</button>
        </form>
    </section>
    <section class="history">
        <h2>Recent transactions</h2>
        <div class="table-wrap">
            <table>
                <thead><tr><th>ID</th><th>Type</th><th>Amount</th><th>Other wallet</th><th>Date</th></tr></thead>
                <tbody>
                <% if (transactions.isEmpty()) { %>
                    <tr><td colspan="5" class="muted">No transactions yet.</td></tr>
                <% } else for (WalletTransaction t : transactions) { %>
                    <tr>
                        <td><%= t.getId() %></td><td><%= t.getType() %></td>
                        <td><%= t.getAmount() %> EGP</td>
                        <td><%= t.getOtherWalletNumber() == null ? "-" : t.getOtherWalletNumber() %></td>
                        <td><%= t.getCreatedAt() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>

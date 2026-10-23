package com.student.ewallet.web;

import com.student.ewallet.model.Wallet;
import com.student.ewallet.service.WalletService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/wallet")
public class WalletServlet extends HttpServlet {
    private final WalletService service = new WalletService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        Wallet wallet = service.getWallet(userId);
        req.setAttribute("wallet", wallet);
        req.setAttribute("transactions", service.getTransactions(wallet.getWalletNumber()));
        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        try {
            BigDecimal amount = new BigDecimal(req.getParameter("amount"));
            switch (req.getParameter("action")) {
                case "deposit" -> service.deposit(userId, amount);
                case "withdraw" -> service.withdraw(userId, amount);
                case "transfer" -> service.transfer(
                        userId, req.getParameter("receiver"), amount);
                default -> throw new IllegalArgumentException("Unknown operation.");
            }
            req.getSession().setAttribute("message", "Operation completed successfully.");
        } catch (RuntimeException e) {
            req.getSession().setAttribute("error", "Operation failed: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/wallet");
    }
}

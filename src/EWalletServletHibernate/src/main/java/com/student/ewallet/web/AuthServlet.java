package com.student.ewallet.web;

import com.student.ewallet.model.User;
import com.student.ewallet.service.WalletService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private final WalletService service = new WalletService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            User user;
            if ("register".equals(action)) {
                user = service.register(req.getParameter("name"),
                        req.getParameter("email"), req.getParameter("password"));
            } else {
                user = service.login(req.getParameter("email"), req.getParameter("password"));
            }
            req.getSession().setAttribute("userId", user.getId());
            resp.sendRedirect(req.getContextPath() + "/wallet");
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(req, resp);
        }
    }
}

package com.student.ewallet.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/wallet", "/logout"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        if (req.getSession(false) == null ||
                req.getSession(false).getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }
        chain.doFilter(request, response);
    }
}

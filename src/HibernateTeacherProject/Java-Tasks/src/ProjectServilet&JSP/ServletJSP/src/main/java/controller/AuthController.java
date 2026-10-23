package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import service.AccountService;
import service.impl.AccountServiceImpl;


public class AuthController extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("login.html");
            return;
        }

        switch (action) {
            case "login":
                login(request, response);
                break;

            case "signup":
                signup(request, response);
                break;

            case "logout":
                logout(request, response);
                break;

            case "forgotPassword":
                forgotPassword(request, response);
                break;

            case "deleteAccount":
                deleteAccount(request, response);
                break;

            default:
                goToErrorPage(request, response, "Invalid auth action");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = accountService.login(username, password);

        if (account != null) {
            createSessionAndCookie(request, response, account);
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Invalid username or password");
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setPassword(password);

        boolean saved = accountService.signup(account);

        if (saved) {
            Account loggedAccount = accountService.login(username, password);
            createSessionAndCookie(request, response, loggedAccount);
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Signup failed");
        }
    }

    private void createSessionAndCookie(HttpServletRequest request, HttpServletResponse response, Account account) {

        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", account);

        Cookie cookie = new Cookie("username", account.getUsername());
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect("login.html");
    }

    private void forgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        boolean updated = accountService.updatePassword(email, newPassword);

        if (updated) {
            response.sendRedirect("login.html");
        } else {
            goToErrorPage(request, response, "Email not found");
        }
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.html");
            return;
        }

        Account account = (Account) session.getAttribute("loggedInUser");

        boolean deleted = accountService.deleteAccount(account.getId());

        if (deleted) {
            session.invalidate();
            response.sendRedirect("signup.html");
        } else {
            goToErrorPage(request, response, "Account was not deleted");
        }
    }

    private void goToErrorPage(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {

        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
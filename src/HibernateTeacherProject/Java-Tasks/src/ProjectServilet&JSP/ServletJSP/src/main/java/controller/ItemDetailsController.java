package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemDetails;
import service.ItemDetailsService;
import service.impl.ItemDetailsServiceImpl;


public class ItemDetailsController extends HttpServlet {

    private ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl();

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

        if (!isLoggedIn(request)) {
            response.sendRedirect("login.html");
            return;
        }

        String action = request.getParameter("action");

        if (action == null) {
            goToErrorPage(request, response, "Invalid item details action");
            return;
        }

        switch (action) {
            case "showAddDetails":
                showAddDetails(request, response);
                break;

            case "addItemDetails":
                addItemDetails(request, response);
                break;

            case "showUpdateDetails":
                showUpdateDetails(request, response);
                break;

            case "updateItemDetails":
                updateItemDetails(request, response);
                break;

            default:
                goToErrorPage(request, response, "Invalid item details action");
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    private void showAddDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long itemId = Long.parseLong(request.getParameter("itemId"));

        request.setAttribute("itemId", itemId);
        request.getRequestDispatcher("add-item-details.jsp").forward(request, response);
    }

    private void addItemDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long itemId = Long.parseLong(request.getParameter("itemId"));
        String description = request.getParameter("description");
        String comments = request.getParameter("comments");

        ItemDetails details = new ItemDetails();
        details.setItemId(itemId);
        details.setDescription(description);
        details.setComments(comments);

        boolean saved = itemDetailsService.addItemDetails(details);

        if (saved) {
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Item details was not added");
        }
    }

    private void showUpdateDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long itemId = Long.parseLong(request.getParameter("itemId"));

        ItemDetails details = itemDetailsService.getDetailsByItemId(itemId);

        if (details != null) {
            request.setAttribute("details", details);
            request.getRequestDispatcher("update-item-details.jsp").forward(request, response);
        } else {
            goToErrorPage(request, response, "Item details not found");
        }
    }

    private void updateItemDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long itemId = Long.parseLong(request.getParameter("itemId"));
        String description = request.getParameter("description");
        String comments = request.getParameter("comments");

        ItemDetails details = new ItemDetails();
        details.setItemId(itemId);
        details.setDescription(description);
        details.setComments(comments);

        boolean updated = itemDetailsService.updateItemDetails(details);

        if (updated) {
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Item details was not updated");
        }
    }

    private void goToErrorPage(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {

        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
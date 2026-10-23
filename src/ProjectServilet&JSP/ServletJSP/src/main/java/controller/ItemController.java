package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import service.ItemService;
import service.impl.ItemServiceImpl;


public class ItemController extends HttpServlet {

    private ItemService itemService = new ItemServiceImpl();

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
            action = "showItems";
        }

        switch (action) {
            case "showItems":
                showItems(request, response);
                break;

            case "addItem":
                addItem(request, response);
                break;

            case "showUpdateItem":
                showUpdateItem(request, response);
                break;

            case "updateItem":
                updateItem(request, response);
                break;

            case "deleteItem":
                deleteItem(request, response);
                break;

            default:
                goToErrorPage(request, response, "Invalid item action");
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    private void showItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Item> items = itemService.getAllItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("show-items.jsp").forward(request, response);
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int totalNumber = Integer.parseInt(request.getParameter("totalNumber"));

        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setTotalNumber(totalNumber);

        boolean added = itemService.addItem(item);

        if (added) {
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Item was not added");
        }
    }

    private void showUpdateItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        Item item = itemService.getItemById(id);

        if (item != null) {
            request.setAttribute("item", item);
            request.getRequestDispatcher("update-item.jsp").forward(request, response);
        } else {
            goToErrorPage(request, response, "Item not found");
        }
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int totalNumber = Integer.parseInt(request.getParameter("totalNumber"));

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);
        item.setTotalNumber(totalNumber);

        boolean updated = itemService.updateItem(item);

        if (updated) {
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Item was not updated");
        }
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        boolean deleted = itemService.deleteItem(id);

        if (deleted) {
            response.sendRedirect("ItemController?action=showItems");
        } else {
            goToErrorPage(request, response, "Item was not deleted");
        }
    }

    private void goToErrorPage(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {

        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
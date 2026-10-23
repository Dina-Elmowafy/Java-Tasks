package com.item.controller;

import com.item.model.Item;
import com.item.service.ItemService;
import com.item.service.impl.ItemServiceImpl;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/ItemController")
public class ItemController extends HttpServlet {

    @Resource(name = "jdbc/item")
    private DataSource dataSource;

    private ItemService itemService;

    @Override
    public void init() {
        itemService = new ItemServiceImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "show-items";
        }

        switch (action) {
            case "add-item":
                showAddForm(request, response);
                break;
            case "update-item":
                showUpdateForm(request, response);
                break;
            case "show-items":
            default:
                showItems(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("update-item".equals(action)) {
            updateItem(request, response);
        } else {
            addItem(request, response);
        }
    }

    private void showItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Item> items = itemService.getAllItems();
        request.setAttribute("allItems", items);
        request.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("formAction", "add-item");
        request.getRequestDispatcher("/WEB-INF/views/item-form.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Item item = itemService.selectItem(id);

        if (item == null) {
            response.sendRedirect(request.getContextPath() + "/ItemController");
            return;
        }

        request.setAttribute("item", item);
        request.setAttribute("formAction", "update-item");
        request.getRequestDispatcher("/WEB-INF/views/item-form.jsp").forward(request, response);
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Item item = readItemFromRequest(request, false);
        itemService.addItem(item);
        response.sendRedirect(request.getContextPath() + "/ItemController");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Item item = readItemFromRequest(request, true);
        itemService.updateItem(item);
        response.sendRedirect(request.getContextPath() + "/ItemController");
    }

    private Item readItemFromRequest(HttpServletRequest request, boolean includeId) {
        Item item = new Item();

        if (includeId) {
            item.setId(Long.parseLong(request.getParameter("id")));
        }

        item.setName(request.getParameter("name").trim());
        item.setPrice(Double.parseDouble(request.getParameter("price")));
        item.setTotalNumber(Integer.parseInt(request.getParameter("totalNumber")));
        return item;
    }
}

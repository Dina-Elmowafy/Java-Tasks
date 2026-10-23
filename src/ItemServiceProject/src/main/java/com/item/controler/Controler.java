package com.item.controler;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.itemService;
import com.item.service.impl.itemServiceImpl;

@WebServlet("/Controler")
public class Controler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/item")
    private DataSource dataSource;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (Objects.isNull(action)) {
            action = "show-items";
        }

        switch (action) {

            case "show-item":
                showItem(request, response);
                break;

            case "show-items":
                showItems(request, response);
                break;

            case "update-item":
                updateItem(request, response);
                break;

            case "add-item":
                addItem(request, response);
                break;

            case "delete-item":
                deleteItem(request, response);
                break;

            default:
                showItems(request, response);
                break;
        }
    }

    private void showItems(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        itemService itemService =
                new itemServiceImpl(dataSource);

        List<Item> items = itemService.getAllItem();

        request.setAttribute("getAllItem", items);

        request.getRequestDispatcher("/WEB-INF/show-items.jsp")
               .forward(request, response);
    }

    private void showItem(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // سيتم تنفيذه لاحقًا
    }

    private void addItem(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // سيتم تنفيذه لاحقًا
    }

    private void updateItem(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // سيتم تنفيذه لاحقًا
    }

    private void deleteItem(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
    	Long id =Long.parseLong(request.getParameter("id")) ;
    	itemService itemService =
        new itemServiceImpl(dataSource);
        boolean isItemDeleted=itemService.deleteItem(id);
        
    	if(isItemDeleted)
    	{
    		  try {
				showItems(request, response);
			} catch (ServletException e) {
				System.out.println("Execption"+" "+e);
			} catch (IOException e) {
				System.out.println("Execption"+" "+e);
			}
    	}

    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        doGet(request, response);
    }
}
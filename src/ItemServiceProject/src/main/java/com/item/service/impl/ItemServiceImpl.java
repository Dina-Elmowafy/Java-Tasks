package com.item.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.itemService;

public class itemServiceImpl implements itemService {

    private DataSource dataSource;

    public itemServiceImpl(DataSource dataSource) {

        if (Objects.isNull(dataSource)) {
            throw new IllegalArgumentException(
                "DataSource must not be Null"
            );
        }

        this.dataSource = dataSource;
    }

    @Override
    public List<Item> getAllItem() {

        String query = "SELECT * FROM ITEM";
        List<Item> items = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                Item objectItem = new Item(
                    resultSet.getLong("ID"),
                    resultSet.getString("NAME"),
                    resultSet.getDouble("PRICE"),
                    resultSet.getDouble("TOTAL_NUMBER")
                );

                items.add(objectItem);
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Item selectItem() {
        return null;
    }

    @Override
    public boolean updatItem(Long id) {
        return false;
    }

    @Override
    public boolean deleteItem(Long id) {
    	  Connection connection =null;
    	   Statement statement  =null;

          try {
        		   connection = dataSource.getConnection();
                   statement = connection.createStatement();
        	       String query = "DELETE FROM ITEM WHERE ID =" + id;
                   statement.execute(query);
                   return true;
          }
    

          catch (SQLException e) {
              System.out.println("Exception: " + e.getMessage());
              e.printStackTrace();
          }
           return false;
      }
    

    @Override
    public boolean addIteam(Long id) {
        return false;
    }
}
package com.item.service.impl;

import com.item.model.Item;
import com.item.service.ItemService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemServiceImpl implements ItemService {

    private final DataSource dataSource;

    public ItemServiceImpl(DataSource dataSource) {
        if (Objects.isNull(dataSource)) {
            throw new IllegalArgumentException("DataSource must not be null");
        }
        this.dataSource = dataSource;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT id, name, price, total_number FROM item ORDER BY id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                items.add(readItem(resultSet));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot load items", exception);
        }

        return items;
    }

    @Override
    public Item selectItem(Long id) {
        String sql = "SELECT id, name, price, total_number FROM item WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return readItem(resultSet);
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot load item", exception);
        }

        return null;
    }

    @Override
    public boolean addItem(Item item) {
        String sql = "INSERT INTO item (name, price, total_number) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getTotalNumber());
            return statement.executeUpdate() == 1;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot add item", exception);
        }
    }

    @Override
    public boolean updateItem(Item item) {
        String sql = "UPDATE item SET name = ?, price = ?, total_number = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getTotalNumber());
            statement.setLong(4, item.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot update item", exception);
        }
    }

    private Item readItem(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getInt("total_number")
        );
    }
}

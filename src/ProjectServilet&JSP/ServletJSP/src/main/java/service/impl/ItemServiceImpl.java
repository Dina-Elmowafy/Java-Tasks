package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import service.ItemService;
import util.DBConnection;

public class ItemServiceImpl implements ItemService {

    @Override
    public boolean addItem(Item item) {

        String sql = "INSERT INTO item (id, name, price, total_number) VALUES (item_seq.nextval, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getTotalNumber());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Item> getAllItems() {

        List<Item> items = new ArrayList<>();

        String sql =
                "SELECT i.id, i.name, i.price, i.total_number, " +
                "CASE WHEN d.id IS NULL THEN 0 ELSE 1 END AS has_details " +
                "FROM item i LEFT JOIN item_details d ON i.id = d.item_id " +
                "ORDER BY i.id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setTotalNumber(rs.getInt("total_number"));
                item.setHasDetails(rs.getInt("has_details") == 1);

                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Item getItemById(long id) {

        String sql = "SELECT id, name, price, total_number FROM item WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();

                    item.setId(rs.getLong("id"));
                    item.setName(rs.getString("name"));
                    item.setPrice(rs.getDouble("price"));
                    item.setTotalNumber(rs.getInt("total_number"));

                    return item;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateItem(Item item) {

        String sql = "UPDATE item SET name = ?, price = ?, total_number = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getTotalNumber());
            ps.setLong(4, item.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(long id) {

        String deleteDetailsSql = "DELETE FROM item_details WHERE item_id = ?";
        String deleteItemSql = "DELETE FROM item WHERE id = ?";

        try (Connection connection = DBConnection.getConnection()) {

            try (PreparedStatement ps1 = connection.prepareStatement(deleteDetailsSql)) {
                ps1.setLong(1, id);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = connection.prepareStatement(deleteItemSql)) {
                ps2.setLong(1, id);
                return ps2.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
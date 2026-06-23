package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ItemDetails;
import service.ItemDetailsService;
import util.DBConnection;

public class ItemDetailsServiceImpl implements ItemDetailsService {

    @Override
    public boolean addItemDetails(ItemDetails details) {

        String sql = "INSERT INTO item_details (id, description, comments, item_id) VALUES (item_details_seq.nextval, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, details.getDescription());
            ps.setString(2, details.getComments());
            ps.setLong(3, details.getItemId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ItemDetails getDetailsByItemId(long itemId) {

        String sql = "SELECT id, description, comments, item_id FROM item_details WHERE item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, itemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ItemDetails details = new ItemDetails();

                    details.setId(rs.getLong("id"));
                    details.setDescription(rs.getString("description"));
                    details.setComments(rs.getString("comments"));
                    details.setItemId(rs.getLong("item_id"));

                    return details;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateItemDetails(ItemDetails details) {

        String sql = "UPDATE item_details SET description = ?, comments = ? WHERE item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, details.getDescription());
            ps.setString(2, details.getComments());
            ps.setLong(3, details.getItemId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDetailsByItemId(long itemId) {

        String sql = "DELETE FROM item_details WHERE item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, itemId);
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean hasDetails(long itemId) {
        return getDetailsByItemId(itemId) != null;
    }
}
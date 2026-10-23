package service;

import model.ItemDetails;

public interface ItemDetailsService {

    boolean addItemDetails(ItemDetails details);

    ItemDetails getDetailsByItemId(long itemId);

    boolean updateItemDetails(ItemDetails details);

    boolean deleteDetailsByItemId(long itemId);

    boolean hasDetails(long itemId);
}
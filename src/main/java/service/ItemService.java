package service;

import java.util.List;
import model.Item;

public interface ItemService {

    boolean addItem(Item item);

    List<Item> getAllItems();

    Item getItemById(long id);

    boolean updateItem(Item item);

    boolean deleteItem(long id);
}
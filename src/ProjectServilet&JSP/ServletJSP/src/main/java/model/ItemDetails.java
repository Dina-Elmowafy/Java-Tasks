package model;

public class ItemDetails {

    private long id;
    private String description;
    private String comments;
    private long itemId;

    public ItemDetails() {
    }

    public ItemDetails(long id, String description, String comments, long itemId) {
        this.id = id;
        this.description = description;
        this.comments = comments;
        this.itemId = itemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }    

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
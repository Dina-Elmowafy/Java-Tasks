package com.item.model;

public class Item {

    private Long id;
    private String name;
    private double price;
    private double total_number;

    public Item(
            String name,
            double price,
            double total_number
    ) {
        this.name = name;
        this.price = price;
        this.total_number = total_number;
    }

    public Item(
            Long id,
            String name,
            double price,
            double total_number
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.total_number = total_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalNumber() {
        return total_number;
    }

    public void setTotalNumber(double total_number) {
        this.total_number = total_number;
    }
}
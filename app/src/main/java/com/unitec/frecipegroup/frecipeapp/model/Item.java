package com.unitec.frecipegroup.frecipeapp.model;

/**
 * Created by Yasmin on 15-May-17.
 */

public class Item {
    private static int count = 1;
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private String category;
    private String expiryDate;

    public Item() {
        count++;
    }

    public Item(String name, double quantity, String unit, String category, String expiryDate) {
        this.id = count;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
        this.expiryDate = expiryDate;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit + ", category=" + category + ", expiryDate=" + expiryDate
                + "]";
    }
}

package com.unitec.frecipegroup.frecipeapp.model;

/**
 * Created by Yasmin on 15-May-17.
 */

public class Ingredient {
    private static int count = 1;
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private Recipe recipe;

    public Ingredient() {
        count++;
    }

    public Ingredient(String name, double quantity, String unit, Recipe recipe) {
        this.id = count;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.recipe = recipe;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit + ", recipe=" + recipe.getId()
                + "]";
    }
}

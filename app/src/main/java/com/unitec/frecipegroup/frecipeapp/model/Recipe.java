package com.unitec.frecipegroup.frecipeapp.model;

import java.util.List;

/**
 * Created by Yasmin on 15-May-17.
 */

public class Recipe {
    private static int count = 1;
    private int id;
    private String name;
    private String description;
    private String method;
    private String category;
    private List<Ingredient> ingredients;

    public Recipe() {
        count++;
    }

    public Recipe(String name, String description, String method, String category) {
        this.id = count;
        this.name = name;
        this.description = description;
        this.method = method;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
                + "]";
    }
}

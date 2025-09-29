package com.demo.models;

public class Item {
    String name;
    String category;

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }


}

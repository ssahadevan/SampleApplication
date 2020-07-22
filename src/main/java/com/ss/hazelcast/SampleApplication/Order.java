package com.ss.hazelcast.SampleApplication;

import java.util.List;

public class Order {
    private final String id;
    private final String description;
    private List<Item> items;

    public Order(String id, String description, List<Item> items) {
        this.id = id;
        this.description = description;
        this.items= items;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }
}

package com.ss.hazelcast.SampleApplication;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private final String id;
    private final String description;
    private List<Item> items;
    // Status of Order
    private String status;

    public Order(String id, String description, List<Item> items) {
        this.id = id;
        this.description = description;
        this.items= items;
        this.status="Available";
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

    public String getStatus() { return status ;}

    public void setStatus( String Status) { this.status=status;}
}

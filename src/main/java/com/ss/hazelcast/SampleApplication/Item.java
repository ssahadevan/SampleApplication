package com.ss.hazelcast.SampleApplication;

import java.io.Serializable;

public class Item implements Serializable {
    private final String id;
    private final String name;
    private final double price;


    public Item(String id, String name, double price ) {
        this.id = id;
        this.name = name;
        this.price= price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

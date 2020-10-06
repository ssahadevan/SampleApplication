package com.ss.hazelcast.SampleApplication.payments.dao;

public class Merchant {

    private long id;
    private String name;

    private void Merchant( long id, String name )
    {
        this.id=id;
        this.name=name;
    }
}

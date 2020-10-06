package com.ss.hazelcast.SampleApplication.payments.dao;

public class Account {

    private long id;
    Person person;

    private void Account(long id, Person person )
    {
        this.id = id ;
        this.person=person;
    }
}

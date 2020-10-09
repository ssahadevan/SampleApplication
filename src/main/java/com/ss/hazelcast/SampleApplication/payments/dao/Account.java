package com.ss.hazelcast.SampleApplication.payments.dao;

public class Account {

    private long id;
    Person person;
    private long cardNumber;

    private void Account(long id, Person person, long cardNumber )
    {
        this.id = id ;
        this.person=person;
        this.cardNumber=cardNumber;
    }
}

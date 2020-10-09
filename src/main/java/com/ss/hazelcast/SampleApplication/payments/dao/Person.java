package com.ss.hazelcast.SampleApplication.payments.dao;

import java.io.Serializable;


public class Person implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;

    public void Person(long id ,  String firstName, String middleName , String lastName)
    {
        this.id=id;
        this.firstName=firstName;
        this.middleName = middleName;
        this.lastName=lastName;
    }

    public long getId(){

        return this.id;
    }
    public String getFirstName()
    {
        return this.firstName;
    }

    public String getMiddleName()
    {
        return this.middleName;
    }

    public String getLastName()
    {
        return this.lastName;
    }
}

package com.ss.hazelcast.SampleApplication.payments.dao;

import java.util.Date;

public class Transaction {
    private long id ;
    private Date txndate;
    private long accountId;
    private Person person;
    private double amount;
    private String currency;
    private Merchant merchant;
    private int fraudScore;

    public void Transaction ( long id, Date txndate
            , long accountId
            , Person person
            , double amount
            , String currency
            , Merchant merchant
            , int fraudScore
    )
    {
        this.id=id;
        this.txndate=txndate;
        this.accountId=accountId;
        this.person=person;
        this.amount=amount;
        this.currency=currency;
        this.merchant=merchant;
        this.fraudScore=fraudScore;
    }
}

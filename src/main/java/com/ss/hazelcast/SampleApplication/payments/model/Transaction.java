package com.ss.hazelcast.SampleApplication.payments.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Transaction implements Serializable {

    private long id;
    private String date;
    private String merchantName;
    private double amount;

    private int fraudScore;


}
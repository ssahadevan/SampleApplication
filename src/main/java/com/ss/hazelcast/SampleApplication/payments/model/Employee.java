package com.ss.hazelcast.SampleApplication.payments.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Employee {

    private String name;
    private Double salary;
    private EmployeePosition position;
}

package com.ss.hazelcast.SampleApplication.ClusterSizer.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Builder
public class Input implements Serializable {
    private int expectedDataSize;
    private int backupsRequired;
    private double readWriteRatio;
    private int latencyRequirement;
}

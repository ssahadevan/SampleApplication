package com.ss.hazelcast.SampleApplication.ClusterSizer.service;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import com.ss.hazelcast.SampleApplication.payments.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ss.hazelcast.SampleApplication.ClusterSizer.dao.Input;
import org.springframework.stereotype.Service;

@Service
public class ClusterSizeService{

    private HazelcastClientUtility hazelcastClientUtility;
    private String transactionMap = "ClusterSizeMap";

    public ClusterSizeService() {
        hazelcastClientUtility = null;
    }

     /* Calculate the Cluster Size*/
    public int getClusterSize( Input input) {
      int clusterSize= input.getExpectedDataSize() + input.getExpectedDataSize() * input.getBackupsRequired();
      return clusterSize;
    }

    public String getMapName() {
        return transactionMap;
    }





}

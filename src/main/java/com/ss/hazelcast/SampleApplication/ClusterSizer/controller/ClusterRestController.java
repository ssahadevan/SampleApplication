package com.ss.hazelcast.SampleApplication.ClusterSizer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ClusterRestController {

    private static final String template = "Product is, %s!";
    private final AtomicLong counter = new AtomicLong();
    private int defaultNodeCount=3;
    /* Max supported memory per node in GB */
    private int maxMemoryPerNode=100;

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;


    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");


    /* Gets the recommended default cluster size */
    @GetMapping("/cluster/default")
    public String getDefaultClusterSize() {

        return "Default Cluster size :" + defaultNodeCount;
    }

    /* Gets the recommended default cluster size */
    @GetMapping("/cluster/memory/getSize")
    public String getClusterMemorySize(@RequestParam(defaultValue = "empty") int expectedDataSize,
                                 @RequestParam(defaultValue = "empty") int numberOfBackups
                                ) {
        int clusterMemorySize = expectedDataSize + ( expectedDataSize * numberOfBackups );
        int numberOfNodes = clusterMemorySize / maxMemoryPerNode;
        if ( numberOfNodes < 3)
        {
            numberOfNodes= defaultNodeCount;
        }
        return "Cluster Memory size is  :"
                + clusterMemorySize + " GB , numberOfNodes =" + numberOfNodes ;
    }


}

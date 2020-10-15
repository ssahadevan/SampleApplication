package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastMember {

    /*
     * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     *
     * http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */


    public static void main(String[] args) {
        // com.ss.ExampleDOL example = new com.ss.ExampleDOL();
        /* Shows Programatic config.
        Config config = new Config();
        config.getManagementCenterConfig().setScriptingEnabled(true);
        RestApiConfig restApiConfig = new RestApiConfig()
                .setEnabled(true)
                .disableAllGroups()
                .enableGroups(RestEndpointGroup.DATA)
                .enableGroups(RestEndpointGroup.CLUSTER_READ)
                .enableGroups(RestEndpointGroup.CLUSTER_WRITE)
                .enableGroups(RestEndpointGroup.HEALTH_CHECK)
                ;
        config.getNetworkConfig().setRestApiConfig(restApiConfig);
        */
        Config config = new ClasspathXmlConfig("hazelcast.xml");
        HazelcastInstance node1 = Hazelcast.newHazelcastInstance(config);
        HazelcastInstance node2 = Hazelcast.newHazelcastInstance(config);
        HazelcastInstance node3 = Hazelcast.newHazelcastInstance(config);
        /* Use this to validate the member is connected to Mongo
        IMap<String, Supplement> supplements = node1.getMap("supplements");
        System.out.println(supplements.size());

        supplements.set("4", new Supplement("bcaa", 10));
        supplements.set("5", new Supplement("protein", 100));
        supplements.set("6", new Supplement("glucosamine", 200));

        System.out.println(supplements.size());

        supplements.evictAll();

        System.out.println(supplements.size());

        supplements.loadAll(true);

        System.out.println(supplements.size());
        */
        System.out.printf("Hazelcast Cluster is up and running");
    }



}

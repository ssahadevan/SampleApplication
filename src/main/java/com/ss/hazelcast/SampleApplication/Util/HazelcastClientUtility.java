package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Map;

public class HazelcastClientUtility {
    private static String localAddress = "127.0.0.1";
    private HazelcastInstance client=null ;
    private String nameOfMap;
    private String key;
    private Object value;

    public HazelcastClientUtility() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(localAddress);

        client = HazelcastClient.newHazelcastClient(clientConfig);
        System.out.println(clientConfig.toString());
    }


    /*
    Shutdown the Hazelcast Client connection
     */
    public void shutdown() {
        HazelcastClient.shutdownAll();
    }

    public void put(String nameOfMap , String key , String value) {
        IMap<String, Object> myMap = client.getMap( nameOfMap );
        System.out.println("Calling map.put");
        myMap.put(key, value);
    }

    public void put(String nameOfMap , String key , Object value) {
        this.nameOfMap = nameOfMap;
        this.key = key;
        this.value = value;
        IMap<String, Object> myMap = client.getMap( nameOfMap );
        System.out.println("Calling map.put");
        myMap.put(key, value);
    }

    public void set(String nameOfMap , String key , String value) {
        IMap<String, Object> myMap = client.getMap( nameOfMap );
        System.out.println("Calling map.set");
        myMap.set(key, value);
    }

    public void set(String nameOfMap , String key , Object value) {
        this.nameOfMap = nameOfMap;
        this.key = key;
        this.value = value;
        IMap<String, Object> myMap = client.getMap( nameOfMap );
        System.out.println("Calling map.set");
        myMap.set(key, value);
    }


    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(localAddress);


        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        // System.out.println(clientConfig.toString());

        HazelcastClient.shutdownAll();
    }

    public Object get(String nameOfMap, String key) {
        IMap<String, String> myMap = client.getMap( nameOfMap );
        Object value = myMap.get(key) ;
        System.out.println("value is " + String.valueOf(value));

        return ( value );
    }

    public Map getAll( String nameOfMap ) {
        Map<String, String> map = client.getMap(nameOfMap);
        /*
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

         */
        return map;
        }

    public int getSize(String nameOfMap ) {
        IMap<String, String> myMap = client.getMap( nameOfMap );
        int size = myMap.size() ;
        System.out.println("Map size is " + size);
        //Product product = new Product( key , value.toString() );
        return ( size );
    }


}

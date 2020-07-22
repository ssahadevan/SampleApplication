package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.ss.hazelcast.SampleApplication.Product;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;

public class HazelcastClientUtility {
    private static String localAddress = "127.0.0.1";
    private HazelcastInstance client=null ;

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
        myMap.put(key, value);
    }

    /*
    public Object get(String nameOfMap , String key ) {
        IMap<String, Object> myMap = client.getMap( nameOfMap );
        Object value = myMap.get(key) ;
        System.out.println("value is " + String.valueOf(value));
        return ( value );
    }

     */

    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(localAddress);


        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        System.out.println(clientConfig.toString());

        BlockingQueue<String> queue = client.getQueue("queue");
        queue.put("Hello!");
        System.out.println("Message sent by Hazelcast Client!");


        IMap<String, Object> myMap = client.getMap("myMap");
        myMap.put("1", "Sharath");

        System.out.println("myMap value is " + myMap.get("1")) ;

        myMap.lock("1");

        try
        {
            // critical section code.


            Collection distributedObjects = client.getDistributedObjects();

            distributedObjects.forEach(
                    distributedObject->System.out.println(distributedObject));

        }
        finally
        {
            myMap.unlock("1");
        }

        HazelcastClient.shutdownAll();
    }

    public Product get(String nameOfMap, String key) {
        IMap<String, String> myMap = client.getMap( nameOfMap );
        Object value = myMap.get(key) ;
        System.out.println("value is " + String.valueOf(value));
        Product product = new Product( key , value.toString() );
        return (product );
    }

    public int getSize(String nameOfMap ) {
        IMap<String, String> myMap = client.getMap( nameOfMap );
        int size = myMap.size() ;
        System.out.println("Map size is " + size);
        //Product product = new Product( key , value.toString() );
        return ( size );
    }

    /*
    public Order get(String nameOfMap, String key) {
        IMap<String, String> myMap = client.getMap( nameOfMap );
        Object value = myMap.get(key) ;
        System.out.println("value is " + String.valueOf(value));
        Order order = new Order( key , value.toString() );
        return ( order );
    }

     */
}

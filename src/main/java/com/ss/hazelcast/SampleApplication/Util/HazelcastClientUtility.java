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
        myMap.put(key, value);
    }

    public void put(String nameOfMap , String key , Object value) {
        this.nameOfMap = nameOfMap;
        this.key = key;
        this.value = value;
        IMap<String, Object> myMap = client.getMap( nameOfMap );
        myMap.put(key, value);
    }


    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(localAddress);


        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        // System.out.println(clientConfig.toString());

        IMap<String, Supplement> supplements = client.getMap("supplements");

        System.out.println("Supplements Size=" +  supplements.size());
        try {
            supplements.put("1", new Supplement("bcaa_1", 10));
            supplements.put("2", new Supplement("protein_2", 100));
            supplements.put("3", new Supplement("glucosamine_3", 200));
            supplements.put("4", new Supplement("bcaa_4", 10));
            supplements.put("5", new Supplement("protein_5", 100));
            supplements.put("6", new Supplement("glucosamine_6", 200));
            supplements.put("7", new Supplement("vitaminD_7", 200));
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }

        System.out.println(supplements.size());

        supplements.evictAll();

        System.out.println(supplements.size());

        supplements.loadAll(true);

        System.out.println(supplements.size());
        Supplement supplement=null;

        IMap<String, Supplement> supplementMap = client.getMap("supplements");
        for ( int k=1; k<= supplements.size() ; k++)
        {
            supplement = supplementMap.get(String.valueOf(k));
            if ( supplement != null) {
                System.out.println("Supplement is " + supplement.getName()
                        + ", price =" + supplement.getPrice());
            }
        }
        int size=supplements.size();
        for ( int i=1; i <= size ; i++) {
            System.out.println("Deleting " + i );
            supplements.delete(String.valueOf(i));
        }


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

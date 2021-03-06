package com.ss.hazelcast.SampleApplication;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderControlller {

    /* Get a Specific Key */
    @GetMapping("/order/{id}")
    public Order getOrder( @PathVariable String id ) {
        // template="Order is ";
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        // Order Order=  new Order(counter.incrementAndGet(), String.format(template, name));
        Item item= new Item("1", "itemOne", 10.50);
        List items = new ArrayList();
        items.add(item);
        Order order= new Order ("1", "firstOrder", items);
        hazelcastClientUtility.put("Order",
                String.valueOf( order.getId() ), order );

        return order;
    }

    /* Get the count of  Orders */
    @GetMapping("/order/count")
    public int getOrderCount() {

        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        return  hazelcastClientUtility.getSize("Order" );

        // return value;
    }
}

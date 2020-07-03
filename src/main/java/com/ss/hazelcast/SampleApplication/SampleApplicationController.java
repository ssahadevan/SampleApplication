
package com.ss.hazelcast.SampleApplication;


import java.util.concurrent.atomic.AtomicLong;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleApplicationController {

    private static final String template = "Product is, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/product")
    public Product product(@RequestParam(value = "name", defaultValue = "World") String name) {
       // template="Product is ";
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        Product product=  new Product(counter.incrementAndGet(), String.format(template, name));
        hazelcastClientUtility.put("Product", counter.toString() , product.getName() );

        return product;
    }
}

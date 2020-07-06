package com.ss.hazelcast.SampleApplication;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class SampleApplicationController {

    private static final String template = "Product is, %s!";
    private final AtomicLong counter = new AtomicLong();

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        System.out.println("Message is " + message );
        return "welcome - Message is " + message ; //view
    }


    @GetMapping("/products/{id}")
    public Product getProduct( @PathVariable String id ) {
       // template="Product is ";
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        // Product product=  new Product(counter.incrementAndGet(), String.format(template, name));
        Product product = hazelcastClientUtility.get("Product", id  );

        return product;
    }

    @PostMapping("/products")
    public Product newProduct(@RequestBody Product newProduct) {
        // template="Product is ";
        System.out.println("In product") ;
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        // Product product=  new Product(counter.incrementAndGet(), String.format(template, name));
        hazelcastClientUtility.put("Product",  String.valueOf( newProduct.getId() ), newProduct.getName() );

        return newProduct;
    }
}

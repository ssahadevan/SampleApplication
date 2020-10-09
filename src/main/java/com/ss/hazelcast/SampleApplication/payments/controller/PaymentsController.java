package com.ss.hazelcast.SampleApplication.payments.controller;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import com.ss.hazelcast.SampleApplication.payments.dao.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;



// @RestController
@Controller
public class PaymentsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    /*
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    */


    @GetMapping("/payments")
    public String payments(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {

        model.addAttribute( "name" , name );
        // Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name))  ;
        // model.addAttribute("greeting" , greeting);
        return "payments";
    }

    @GetMapping("/payments/persons")
    public String persons(@RequestParam(value = "id", defaultValue = "1") long id, Model model) {
        model.addAttribute( "id" , id );
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();
        Person person=(Person) hazelcastClientUtility.get("Person", String.valueOf(id) );
        model.addAttribute( "Person" , person );
        model.addAttribute("firstName", person.getFirstName());
        model.addAttribute("lastName", person.getLastName());
        return "persons";
    }

    /*
    @GetMapping("/payments/transactions")
    public String transactions(@RequestParam(value = "id", defaultValue = "1") long id, Model model) {
        model.addAttribute( "id" , id );
        //HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();
        //Transaction transaction=(Transaction) hazelcastClientUtility.get("Transaction", String.valueOf(id) );
        model.addAttribute( "date" , "01/28/2020" );
        model.addAttribute("merchantName", "BBY");
        model.addAttribute("amount", "255.12");
        return "transactions";
    }
    */

}


package com.ss.hazelcast.SampleApplication.payments.controller;

import com.ss.hazelcast.SampleApplication.payments.dao.Person;
import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class PersonController {

    private static final String template = "Product is, %s!";
    private final AtomicLong counter = new AtomicLong();

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

      /* Get a Specific Key */
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable long id ) {

        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        return ( Person ) hazelcastClientUtility.get("Person", String.valueOf(id) );
    }

    /* Get the count of  Person */
    @GetMapping("/person/count")
    public int getPersonCount() {

        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        return hazelcastClientUtility.getSize("Person" ) ;

    }

    @PostMapping("/person")
    public Person newPerson(@RequestBody Person newPerson) {

        System.out.println("In person") ;
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();

        hazelcastClientUtility.put("Person",  String.valueOf( newPerson.getId() ), newPerson );

        return newPerson;
    }
}


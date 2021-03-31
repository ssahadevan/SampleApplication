package com.ss.hazelcast.SampleApplication.payments.controller;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import com.ss.hazelcast.SampleApplication.payments.dao.Transaction;
import com.ss.hazelcast.SampleApplication.payments.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



@RestController
public class TransactionRestController {

    private static final String template = "Product is, %s!";
    private final AtomicLong counter = new AtomicLong();

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;


    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    /* Get a Specific Key */
    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable long id ) {

        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();
        return (Transaction) hazelcastClientUtility.get("Transaction", String.valueOf(id) );
    }


    /* Gets a transactions count */
    @GetMapping("transactions/count")
    public String getTransactionsCount(Model model) {
        TransactionService transactionService = new TransactionService() ;
        int transactionCount =  transactionService.getTransactionsCount();
        // model.addAttribute("transactionsCount", transactionCount);
        return "Number of Transactions is :" + transactionCount;
    }


}



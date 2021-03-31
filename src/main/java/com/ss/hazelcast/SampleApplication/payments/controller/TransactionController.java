package com.ss.hazelcast.SampleApplication.payments.controller;

import com.ss.hazelcast.SampleApplication.payments.model.Transaction;
import com.ss.hazelcast.SampleApplication.payments.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private boolean useSet=false;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /* Gets a transaction */
    @GetMapping("transactions")
    public String transactions(Model model) {

        model.addAttribute("transactions", transactionService.generateTransactionList());
        // model.addAttribute("bySalary", Comparator.comparing(Transaction::getSalary));

        return "transactions";
    }


    /* Adds a Transaction */
    @PostMapping("transactions")
    public String transactions(@RequestBody Transaction transaction, Model model) {
        transactionService.create(transaction, useSet);
        model.addAttribute("transactions", transaction);
        // model.addAttribute("bySalary", Comparator.comparing(Transaction::getSalary));

        return "transactions";
    }

    /* Gets useSet */
    @GetMapping("isUseSet")
    public String isUseSet(Model model) {
        model.addAttribute("message", "useSet=" + useSet);
        return "index";
    }

    /* Sets the useSet flag */
    @PostMapping("setUseSet")
    public String setUseSet(@RequestBody String inputValue, Model model) {
        if ( inputValue.contains("true"))
            useSet=true;
        else
            useSet=false;

        System.out.println("this.useSet=" + useSet + ", inputValue = " + inputValue);
        model.addAttribute("message", "useSet=" + useSet);
        return "index";
    }
}
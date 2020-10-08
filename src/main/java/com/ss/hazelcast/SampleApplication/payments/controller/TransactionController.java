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
        transactionService.create(transaction);
        model.addAttribute("transactions", transaction);
        // model.addAttribute("bySalary", Comparator.comparing(Transaction::getSalary));

        return "transactions";
    }
}
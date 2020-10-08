package com.ss.hazelcast.SampleApplication.payments.service;

import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import com.ss.hazelcast.SampleApplication.payments.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    public List<Transaction> generateTransactionList() {

        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();
        Map<String, Transaction> map = hazelcastClientUtility.getAll("Transaction");

        for (Map.Entry<String, Transaction> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        return Arrays.asList(Transaction.builder()
                        .id(1)
                        .date("01/20/2020")
                        .amount(5000.00)
                        .merchantName("BBY")
                        .build(),
                Transaction.builder()
                        .id(2)
                        .date("01/20/2020")
                        .amount(100.00)
                        .merchantName("BBY")
                        .build(),
                Transaction.builder()
                        .id(3)
                        .date("12/20/2019")
                        .amount(33.00)
                        .merchantName("AMZN")
                        .build() );

    }

    public boolean create(Transaction txn)
    {
        HazelcastClientUtility hazelcastClientUtility = new HazelcastClientUtility();
        hazelcastClientUtility.put("Transaction"
                           , String.valueOf(txn.getId())
                           , txn );
        return true ;
    }
}

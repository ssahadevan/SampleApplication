package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.map.IMap;
import com.ss.hazelcast.SampleApplication.payments.model.Transaction;
import com.ss.hazelcast.SampleApplication.payments.service.TransactionService;

import java.util.Map;
import java.util.Random;

public class TransactionConsumer {

    public static void main(String[] args) {

        TransactionService txnService = new TransactionService();

        JetInstance jet = Jet.newJetInstance();

        /* Logs to the screen */

        IMap<String, Transaction> transactionCache = jet.getMap(txnService.getMapName());

        Pipeline p = Pipeline.create();
        /*
        p.readFrom(Sources.map(transactionCache))
         .writeTo(Sinks.logger());
        */

        p.readFrom(Sources.<String, Transaction >map(transactionCache))
                .writeTo(Sinks.map("output")
                );


        p.readFrom(Sources.<String, Transaction >map(transactionCache))
                .writeTo(Sinks.mapWithEntryProcessor("scoredTxn",
                        entry -> entry.getKey(),
                        entry -> new IncrementEntryProcessor(entry.getValue() ))
                );


       /*
        p.readFrom(Sources.<String, Transaction >map(transactionCache))
            .writeTo(Sinks.mapWithUpdating("scoredTxnMap",
                e -> e.getKey(),
                (oldValue, entry) -> (oldValue != null ? oldValue.setFraudScore(entry.getFraudScore())) : null)
        );

        */



        JobConfig cfg = new JobConfig().setName("TransactionConsumer");
    //    JetInstance jet= Jet.bootstrappedInstance().newJob(p, cfg);

     //   JetInstance jet = Jet.newJetInstance();


        jet.newJob(p, cfg).join();
    }

    static class IncrementEntryProcessor implements EntryProcessor<String, Transaction, Transaction> {
        Transaction txn;
        public  IncrementEntryProcessor ( Transaction valueFromStream) {
             txn=valueFromStream;
        }
        @Override
        public Transaction  process(Map.Entry<String, Transaction> entry) {
           // Boolean returnValue = false;
            String key = entry.getKey();
            // Transaction txn = entry.getValue();
            System.out.println("entry is " + entry
                        + ", key is " + key
                        + ", Transactions is " + txn);
            if ( txn != null) {
                Random random = new Random();
                txn.setFraudScore(random.nextInt(1000));
                System.out.println("Fraud Score set ");
                entry.setValue(txn);
                // returnValue=true;
            }

            return txn;

        }
    }
}

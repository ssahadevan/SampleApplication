package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.map.IMap;
import com.ss.hazelcast.SampleApplication.payments.dao.Transaction;
import com.ss.hazelcast.SampleApplication.payments.service.TransactionService;

public class TransactionConsumer {

    public static void main(String[] args) {

        TransactionService txnService = new TransactionService();

        JetInstance jet = Jet.newJetInstance();

        IMap<String, Transaction> transactionCache = jet.getMap(txnService.getMapName());
        Pipeline p = Pipeline.create();
        p.readFrom(Sources.map(transactionCache))
         .writeTo(Sinks.logger());

   /*
        Pipeline p = Pipeline.create();
        p.readFrom(Sources.<Long, Long>mapJournal("streamed-map",
                JournalInitialPosition.START_FROM_CURRENT))
                .withIngestionTimestamps()
                .window(WindowDefinition.tumbling(TimeUnit.SECONDS.toMillis(1)))
                .groupingKey(Map.Entry::getKey)
                .aggregate(AggregateOperations.counting())
                .map(r -> String.format("Key %d had %d updates", r.getKey(), r.getValue()))
                .writeTo(Sinks.logger());
   */
        JobConfig cfg = new JobConfig().setName("TransactionConsumer");
    //    JetInstance jet= Jet.bootstrappedInstance().newJob(p, cfg);

     //   JetInstance jet = Jet.newJetInstance();


        jet.newJob(p, cfg).join();
    }
}

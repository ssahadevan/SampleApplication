package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.aggregate.AggregateOperations;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.JournalInitialPosition;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.jet.pipeline.WindowDefinition;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Consumer {

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();
        p.readFrom(Sources.<Long, Long>mapJournal("streamed-map",
                JournalInitialPosition.START_FROM_CURRENT))
                .withIngestionTimestamps()
                .window(WindowDefinition.tumbling(TimeUnit.SECONDS.toMillis(1)))
                .groupingKey(Map.Entry::getKey)
                .aggregate(AggregateOperations.counting())
                .map(r -> String.format("Key %d had %d updates", r.getKey(), r.getValue()))
                .writeTo(Sinks.logger());

        JobConfig cfg = new JobConfig().setName("consumer");
        Jet.bootstrappedInstance().newJob(p, cfg);
    }
}

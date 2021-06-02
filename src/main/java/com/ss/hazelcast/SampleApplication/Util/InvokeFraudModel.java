package com.ss.hazelcast.SampleApplication.Util;


import com.hazelcast.jet.Jet;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.*;
import com.hazelcast.jet.pipeline.test.TestSources;
import com.hazelcast.jet.python.PythonServiceConfig;
import static com.hazelcast.jet.python.PythonTransforms.mapUsingPython;


public class InvokeFraudModel {
    public static void main(String[] args) {
        Pipeline p = Pipeline.create();
        p.readFrom(TestSources.itemStream(10, (ts, seq) -> String.valueOf(seq)))
                .withoutTimestamps()
                .apply(mapUsingPython(new PythonServiceConfig()
                        .setBaseDir("src/main/resources/python-src")
                        .setHandlerModule("take_sqrt")))
                .setLocalParallelism(1)
                .writeTo(Sinks.logger());

        JobConfig cfg = new JobConfig().setName("python-function");
        Jet.bootstrappedInstance().newJob(p, cfg);
    }
}

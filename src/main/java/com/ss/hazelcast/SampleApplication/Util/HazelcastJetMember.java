package com.ss.hazelcast.SampleApplication.Util;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;

/* Starts a Jet Instance */
public class HazelcastJetMember {

    public static void main(String[] args) throws Exception {
        JetInstance jet1 = Jet.newJetInstance();
       // JetInstance jet2 = Jet.newJetInstance();
       // JetInstance jet3 = Jet.newJetInstance();
    }
}

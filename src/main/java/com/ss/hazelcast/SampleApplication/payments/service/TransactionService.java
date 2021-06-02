package com.ss.hazelcast.SampleApplication.payments.service;

import com.hazelcast.config.IndexConfig;
import com.hazelcast.config.IndexType;
import com.ss.hazelcast.SampleApplication.Util.HazelcastClientUtility;
import com.ss.hazelcast.SampleApplication.payments.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private HazelcastClientUtility hazelcastClientUtility;
    private String transactionMap = "TransactionMap";

    public TransactionService() {
        hazelcastClientUtility = null;
    }

    public List<Transaction> generateTransactionList() {

        if ( hazelcastClientUtility == null ) {
            hazelcastClientUtility = new HazelcastClientUtility();
        }
        Map<String, Transaction> txnMap = hazelcastClientUtility.getAll(transactionMap);

        for (Map.Entry<String, Transaction> entry : txnMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        ArrayList<Transaction> listOfTxns = new ArrayList<Transaction>(txnMap.values());
        return listOfTxns;

    }

    public boolean create(Transaction txn, boolean useSet)
    {

        if ( hazelcastClientUtility == null ) {
            hazelcastClientUtility = new HazelcastClientUtility();
        }

        if ( useSet) {
            hazelcastClientUtility.set(transactionMap
                    , String.valueOf(txn.getId())
                    , txn);
        }
        else
        {
            hazelcastClientUtility.put(transactionMap
                    , String.valueOf(txn.getId())
                    , txn);

        }
        return true ;
    }

    /* Get the count of  Transactions */
    public int getTransactionsCount() {

        if ( hazelcastClientUtility == null ) {
            hazelcastClientUtility = new HazelcastClientUtility();
        }

        return hazelcastClientUtility.getSize(transactionMap) ;

    }

    public String getMapName() {
        return transactionMap;
    }





}

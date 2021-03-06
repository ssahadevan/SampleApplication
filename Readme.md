
# **SampleApplication**
___________________

Goal of the sample application is to demonstrate capabilities of Hazelcast.

### *Key requirements:*

1. Should be able to easily in any env - local, on-prem or public clouds.


#### *To Build:*
__________

`build.sh`

#### *To Start the Hazelcast Cluster*

`startMemberNodes.sh`    - Starts IMDG Members
'startJetNode.sh' - Starts Jet Node

#### *To Start Hazelcast Client*
`runHazelcastclient.sh'


#### *To Launch the Web application:*
________

`run.sh`

#### *To Test the web application:*

Scripts are in bin directory
`test.sh`
`testMany.sh` - Creates multiple Products
`testPerson.sh` - Creates one Person and validates that it exists.
`testTransactions.sh` - Creates 2 Transactions

#### *Data Generation* 
`addTransactions.sh` - Adds many transactions to the Hazelcast Map
`addPersons.sh` - Adds many persons to the Hazelcast map.


The transactions can be viewed at 
http://localhost:8081/transactions

 
#### *Integration with Mongo DB*
Note: Curently commented out
Note the Mongo config in hazelcast.xml, pom.xml,MongoMapStore, Supplement

#### *Check Mongo is running *
On Mac:
`brew services | grep -i mongo | wc -l`

Should return 1 

#### *Mongo Shell
on Mac:
mongo

#### TransactionConsumer

startJetNode.sh
run.sh
addTransactions.sh
runTransactionConsumer.sh - Will read from TransactionMap and write to Output Map









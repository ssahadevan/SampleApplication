# This script will launch a 3 node Hazelcast cluster
# Note: on Upgrade you will have to update the classpath in this script.
HZ_JAR_PATH=/Users/sharathsahadevan/.m2/repository/com/hazelcast/hazelcast-all/4.1/hazelcast-all-4.1.jar
java -classpath /Users/sharathsahadevan/Documents/GitHub/SampleApplication/target/classes:$HZ_JAR_PATH:/Users/sharathsahadevan/.m2/repository/org/mongodb/mongo-java-driver/3.12.7/mongo-java-driver-3.12.7.jar com.ss.hazelcast.SampleApplication.Util.HazelcastMember
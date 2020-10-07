
echo "post persons/1"
curl -X POST http://localhost:8081/person -H 'Content-type:application/json' -d '{"id": 1 , "name": "iPhone 11"}'

echo " get person/1"
curl -X GET  http://localhost:8081/person/1

#!/bin/bash
START=2
END=100
## save $START, just in case if we need it later ##
i=$START
var1='{"id":'
var2=',"firstName":'
firstName=John_
varLastNameHdr=',"lastName":'
lastName=Doe_
varMiddleNameHdr=',"middleName":'
middleName=Rich_
var3='}'
while [[ $i -le $END ]]
do
    echo "$i"
    currentFirstName='"'$firstName$i'"'
    currentLastName=$varLastNameHdr'"'$lastName$i'"'
    currentMiddleName=$varMiddleNameHdr'"'$middleName$i'"'
    echo currentFirstName is $currentFirstName
    #echo $var1$i$var2$i$var3
    var4=$var1'"'$i'"'$var2$currentFirstName$currentLastName$currentMiddleName$var3
    echo var4 is $var4
    curl -X POST http://localhost:8081/person -H 'Content-type:application/json' -d $var4

    ((i = i + 1))
done

echo " "
echo " Number Of Persons is : "
curl -X GET  http://localhost:8081/person/count
echo " "
echo "***  Script completed ***"


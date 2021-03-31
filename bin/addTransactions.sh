echo "post transactions/1"
curl -X POST http://localhost:8081/transactions -H 'Content-type:application/json' -d '{"id": 1 , "date": "09/30/2020", "merchantName": "Walmart" , "amount": 15.23}'

SECONDS=0

#!/bin/bash
START=2
END=2000
## save $START, just in case if we need it later ##
i=$START
var1='{"id":'
var2=',"date":'
merchantName=merchant_
varAmountHdr=',"amount":'
lastName=Doe_
varMerchantNameHdr=',"merchantName":'
middleName=Rich_
currentDate='"09/09/2020"'
var3='}'
while [[ $i -le $END ]]
do
    echo "$i"
    currentFirstName='"'$firstName$i'"'
    currentMerchantName=$varMerchantNameHdr'"'$merchantName$i'"'
    currentAmount=$varAmountHdr'"'$i.$i0'"'

    #echo $var1$i$var2$i$var3
    var4=$var1'"'$i'"'$var2$currentDate$currentMerchantName$currentAmount$var3
    echo var4 is $var4
    curl -X POST http://localhost:8081/transactions -H 'Content-type:application/json' -d $var4

    ((i = i + 1))
done

echo " "
echo " Number Of transactions is : "
curl -X GET  http://localhost:8081/transactions/count
echo " "
date

duration=$SECONDS
echo "$(($duration / 60)) minutes and $(($duration % 60)) seconds elapsed."

echo "***  Script completed ***"
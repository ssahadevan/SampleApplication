
echo "post products/1"
curl -X POST http://localhost:8081/products -H 'Content-type:application/json' -d '{"id": 1 , "name": "iPhone 11"}'

echo " get products/1"
curl -X GET  http://localhost:8081/products/1

#!/bin/bash
START=2
END=100
## save $START, just in case if we need it later ##
i=$START
var1='{"id":'
var2=',"name":'
productName=myProduct_
var3='}'
while [[ $i -le $END ]]
do
    echo "$i"
    currentProductName='"'$productName$i'"'
    echo currentProductName is $currentProductName
    #echo $var1$i$var2$i$var3
    var4=$var1'"'$i'"'$var2$currentProductName$var3
    echo var4 is $var4
    curl -X POST http://localhost:8081/product -H 'Content-type:application/json' -d $var4

    ((i = i + 1))
done

echo " "
echo " Number Of Products is : "
curl -X GET  http://localhost:8081/product/count
echo " "
echo "***  Script completed ***"
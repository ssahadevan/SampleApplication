curl http://localhost:8081/greeting
echo " "
echo " Call with name"
curl "http://localhost:8081/greeting?name=Sharath"
echo ""

echo " Status Check "
curl http://localhost:8081/actuator/health
echo " "
#echo " Call with name"
#curl "http://localhost:8081/product?name=chair"

echo "post products/1"
curl -X POST http://localhost:8081/products -H 'Content-type:application/json' -d '{"id": 1 , "name": "iPhone 11"}'

echo " get products/1"
curl -X GET  http://localhost:8081/products/1
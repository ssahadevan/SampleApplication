curl http://localhost:8081/greeting
echo " "
echo " Call with name"
curl "http://localhost:8081/greeting?name=Sharath"
echo ""

echo " Status Check "
curl http://localhost:8081/actuator/health
echo " "
echo " Call with name"
curl "http://localhost:8081/product?name=chair"

echo "post person/1"
curl -X POST http://localhost:8081/person -H 'Content-type:application/json' -d '{"id": 1 , "firstName": "Sharath" , "lastName": "Sahadevan", "middleName": ""}'

echo " REST - get person/1"
curl -X GET  http://localhost:8081/person/1

echo " Number Of Persons is : "
curl -X GET  http://localhost:8081/person/count
echo " "

echo " Get the Person view ( HTML )  : "
curl -X GET http://localhost:8081/payments/persons
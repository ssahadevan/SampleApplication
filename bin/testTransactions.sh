curl http://localhost:8081/transactions

echo "post transactions/1"
curl -X POST http://localhost:8081/transactions -H 'Content-type:application/json' -d '{"id": 1 , "date": "09/30/2020", "merchantName": "Walmart" , "amount": 15.23}'

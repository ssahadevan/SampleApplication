curl http://localhost:8081/transactions

echo "post transactions/1"
curl -X POST http://localhost:8081/transactions -H 'Content-type:application/json' -d '{"id": 1 , "date": "09/30/2020", "merchantName": "Walmart" , "amount": 15.23 , "fraudScore: 100"}'

echo "post transactions/2"
curl -X POST http://localhost:8081/transactions -H 'Content-type:application/json' -d '{"id": 2 , "date": "09/18/2020", "merchantName": "Cotsco" , "amount": 123.17, "fraudScore: 750" }'

echo "get the transactions"
curl http://localhost:8081/transactions
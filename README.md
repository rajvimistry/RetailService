To Setup this application
1. create a keyspace and add it in application.properties file
2. create a table name product_price in cassandra
3. update cassandra contact points, port and datacenter accordingly in application.properties

To run this application
1. Run -> Run as Spring Boot Application

Endpoints:
[GET] http://localhost:8080/retailservice/products/status
[GET] http://localhost:8080/retailservice/products/12954218
[PUT] http://localhost:8080/retailservice/products/13860428
		Paylaod -> 
		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": {
		        "value": 32.49,
		        "currency_code": "USD"
		    }
		}
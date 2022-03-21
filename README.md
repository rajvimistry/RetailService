To Setup this application
1. create a keyspace and add it in application.properties file
2. create a table name product_price in cassandra

	CREATE TABLE product_price (
    product_id int PRIMARY KEY,
    currency_code text,
    price double
    );
    
Insert Data by using following queries:
    
    use <keyspace name>;
    insert into product_price (product_id, currency_code, price) values (13860428,'USD',13.49);
    insert into product_price (product_id, currency_code, price) values (54456119,'USD',40.49);
    insert into product_price (product_id, currency_code, price) values (13264003,'USD',10.11);
    insert into product_price (product_id, currency_code, price) values (12954218,'USD',15.11);
    
    
3. update cassandra contact points, port and datacenter accordingly in application.properties

To run this application
1. Run -> Run as Spring Boot Application

Endpoints:

[GET] http://localhost:8080/retailservice/products/status

[GET] http://localhost:8080/retailservice/products/12954218

[PUT] http://localhost:8080/retailservice/products/13860428

		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": {
		        "value": 32.49,
		        "currency_code": "USD"
		    }
		}
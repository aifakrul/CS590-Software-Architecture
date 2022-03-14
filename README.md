# CS590-Software-Architecture
Develop a webstore application using microservices with the following functionality.
Design and develop a webstore application using microservices with the following functionality: 
1. The customer service contains functionality to maintain customers (add, remove, update, 
view). A customer has the following attributes: firstName, lastName, Addresss(street, city, 
zip), phone, email. 
2. The products service contains functionality to maintain products (add, remove, update, 
view). A product has the following attributes: product number, name, price, description, 
number in stock. 
3. The shopping cart service contains functionality to maintain shopping carts(create cart, add 
product, remove product, change quantity, view shopping cart) . When you add a product to 
a shopping cart, or change the quantity, the shopping cart service first checks with the 
products service if we have enough products in stock. 
4. The user is able to check out the shopping cart so that the user can place an order. When the 
user checks out the shopping cart, the shopping cart service calls the order service to create 
an order from the current shopping cart. You can then add a customer to the order. When 
the customer places the order, the number-in-stock attribute for all products in the order is 
updated in the products service. Also, when you place the order, the customer service will 
sent an email to the particular customer (simulate sending email by System.out.println()).

Additional requirements: 
1. Because we want to learn from customer behavior related to shopping, the shopping cart 
service is implemented using CQRS and event sourcing. 
2. Because the product service is used a lot, run 3 instances of the product service. 
3. All 4 microservices use the MongoDB database to store data 
4. Messaging is implemented with Kafka 
5. We need to implement logging and tracing in our architecture 
6. We need to use a central config service

Write a standalone client application using the RestTemplate that executes the following 
scenario: 
1. Add a number of products in the product service 
2. Modify a product in the productservice 
3. Retrieve one or more products and show these products (using System.out.println()) 
4. Put some products in the shoppingcart 
5. Retrieve and show the shoppingcart 
6. Delete one product from the shoppingcart 
7. Change the quantity of one of the products 
8. Retrieve and show the shoppingcart 
9. Checkout the shoppingcart 
10. Add customer to order 
11. Retrieve and show the order 
12. Place the order 

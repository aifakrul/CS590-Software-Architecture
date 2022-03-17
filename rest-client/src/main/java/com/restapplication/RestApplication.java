package com.restapplication;

import com.restapplication.domain.cart.CartHolder;
import com.restapplication.domain.cart.ShoppingCart;
import com.restapplication.domain.order.Customer;
import com.restapplication.domain.order.CustomerOrderHolder;
import com.restapplication.domain.order.Order;
import com.restapplication.domain.product.Product;
import com.restapplication.domain.product.Stock;
import com.restapplication.domain.product.StockHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

    @Autowired
    private RestOperations restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String customerUrl = "http://localhost:8082/customer";
        String productUrl = "http://localhost:8083/product";
        String stockUrl = "http://localhost:8083/stock";
        String orderUrl = "http://localhost:8081/order";
        String shoppingCartUrl1 = "http://localhost:8080/cart";
        String shoppingCartUrl2 = "http://localhost:8084/cart";

        /**** 1 ****/
        //add product1
        restTemplate.postForLocation(productUrl, new Product(
               "123",
                "This is test product1",
                1300.0
        ));

        /**** 2 ****/
        //add product2
        restTemplate.postForLocation(productUrl, new Product(
                "1234",
                "This is test product2",
                1500.0
        ));



        // get product1
        Product product1 = restTemplate.getForObject(productUrl + "/{productNumber}", Product.class, "123");
        System.out.println("----------- get product1-----------------------");
        System.out.println(product1);
        // get product2
        Product product2 = restTemplate.getForObject(productUrl + "/{productNumber}", Product.class, "1234");
        System.out.println("----------- get product2-----------------------");
        System.out.println(product2);

//        /**** 2 and 7 ****/
//        //modify product1
        restTemplate.postForLocation(stockUrl, new StockHolder(
                "123",
                98,
                "Fairfield"
        ));

        //modify product2
        restTemplate.postForLocation(stockUrl, new StockHolder(
                "1234",
                198,
                "Iowa"
        ));

        // get modified product1
        Product modProduct1 = restTemplate.getForObject(productUrl + "/{productNumber}", Product.class, "123");
        System.out.println("----------- get modified product1-----------------------");
        System.out.println(modProduct1);
        // get modified product2
        Product modProduct2 = restTemplate.getForObject(productUrl + "/{productNumber}", Product.class, "1234");
        System.out.println("----------- get modified product2-----------------------");
        System.out.println(modProduct2);


        //add product1 to shoppingCart
        restTemplate.postForLocation(shoppingCartUrl2+"/add", new CartHolder(
                "101",
                "123",
                4
        ));

        //add product2 to shoppingCart
        restTemplate.postForLocation(shoppingCartUrl2+"/add", new CartHolder(
                "101",
                "1234",
                5
        ));

        // get shoppingCart
        ShoppingCart cart = restTemplate.getForObject(shoppingCartUrl1 + "/{cartId}", ShoppingCart.class, "101");
        System.out.println("----------- get cart-----------------------");
        System.out.println(cart);


        //delete product2 from shoppingCart
        restTemplate.postForLocation(shoppingCartUrl2+"/remove", new CartHolder(
                "101",
                "1234",
                5
        ));

        // get shoppingCart
        ShoppingCart modifiedCart = restTemplate.getForObject(shoppingCartUrl1 + "/{cartId}", ShoppingCart.class, "101");
        System.out.println("----------- get cart-----------------------");
        System.out.println(modifiedCart);

        //change product1 in shoppingCart
        restTemplate.postForLocation(shoppingCartUrl2+"/change", new CartHolder(
                "101",
                "123",
                15
        ));

        // get shoppingCart
        ShoppingCart modifiedCart2 = restTemplate.getForObject(shoppingCartUrl1 + "/{cartId}", ShoppingCart.class, "101");
        System.out.println("----------- get cart-----------------------");
        System.out.println(modifiedCart2);

        //checkout shoppingCart
        restTemplate.postForLocation(shoppingCartUrl2+"/checkout", new CartHolder(
                "101"
        ));


        // add customer
        restTemplate.postForLocation(customerUrl, new Customer(
                "customer1",
                "Suzy",
                "James",
                "suzyjames@gmail.com",
                "12345678"
        ));

        //getCustomer
        Customer customer = restTemplate.getForObject(customerUrl + "/{customerNumber}", Customer.class, "customer1");
        System.out.println("----------- get customer1-----------------------");
        System.out.println(customer);


        //add customer to order
        restTemplate.postForLocation(orderUrl+"/customer", new CustomerOrderHolder(
                "customer1",
                "101"
        ));

        // get order after placing it
        Order order = restTemplate.getForObject(orderUrl + "/{orderNumber}", Order.class, "101");
        System.out.println("----------- get order-----------------------");
        System.out.println(order);

    }

    @Bean
    RestOperations restTemplate() {
        return new RestTemplate();
    }
}

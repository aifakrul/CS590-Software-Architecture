package com.miu.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SwaCloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaCloudgatewayApplication.class, args);
	}

}

//Call the api-gateway by http://localhost:9090/order/addOrder
//http://localhost:9090/order/Orders
//http://localhost:9090/product/products
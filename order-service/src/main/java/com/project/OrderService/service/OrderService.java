package com.project.OrderService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.OrderService.domain.Customer;
import com.project.OrderService.domain.Order;
import com.project.OrderService.domain.OrderFactory;
import com.project.OrderService.domain.ShoppingCart;
import com.project.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	CustomerFeignClient customerFeignClient;
	public static ObjectMapper objectMapper;

	public OrderService(CustomerFeignClient customerFeignClient) {
		this.customerFeignClient = customerFeignClient;
		OrderService.objectMapper = new ObjectMapper();
	}

	@FeignClient("CustomerService")
	interface CustomerFeignClient {
		@GetMapping(value = "/customer/{customerNumber}")
		public Customer createOrder(@PathVariable String customerNumber);
	}

	public Order getOrder(String orderNumber) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
		if (optOrder.isPresent()) {
			return optOrder.get();
		} else
			return null;
	}

	private String test = "CHECKOUT-FOR-ORDER";

	@KafkaListener(topics = "CHECKOUT-FOR-ORDER", groupId = "checkout")
	public void receive(@Payload String message) {
		System.out.println("value: " + test);
		System.out.println("Receiver received message= " + message);
		ObjectMapper mapper = new ObjectMapper();
		try {

			ShoppingCart cart = mapper.readValue(message, ShoppingCart.class);
			System.out.println("Cart++++++++++++++: " + cart);
			Order order = OrderFactory.createOrder(cart);
			orderRepository.save(order);
			System.out.println("Successfully saved.\n" + mapper.writeValueAsString(order));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCustomer(String customerNumber, String orderNumber) {
		Order order = getOrder(orderNumber);
		if (order == null)
			return;
		Customer customer = customerFeignClient.createOrder(customerNumber);
		if (customer != null) {
			order.setCustomer(customer);
			orderRepository.save(order);
		}
	}

}

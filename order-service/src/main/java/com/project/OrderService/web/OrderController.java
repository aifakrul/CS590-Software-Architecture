package com.project.OrderService.web;

import com.project.OrderService.domain.Customer;
import com.project.OrderService.domain.CustomerOrderHolder;
import com.project.OrderService.domain.Order;
import com.project.OrderService.domain.ShoppingCart;
import com.project.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;

	@GetMapping("/order/{orderNumber}")
	public ResponseEntity<?> getCart(@PathVariable String orderNumber) {
		Order order = orderService.getOrder(orderNumber);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@PostMapping("/order/customer")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerOrderHolder customerOrderHolder) {
		orderService.addCustomer(customerOrderHolder.getCustomerNumber(), customerOrderHolder.getOrderNumber());
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
}

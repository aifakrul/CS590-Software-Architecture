package com.project.OrderService.service;


import com.project.OrderService.domain.Customer;
import com.project.OrderService.domain.Order;
import com.project.OrderService.domain.OrderFactory;
import com.project.OrderService.domain.ShoppingCart;
import com.project.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	CustomerFeignClient customerFeignClient;

	public Order getOrder(String orderNumber) {
		Optional<Order> optOrder = orderRepository.findById(orderNumber);
		if (optOrder.isPresent()) {
			return optOrder.get();
		} else
			return null;
	}
	
	public void createOrder(ShoppingCart cart) {
		Order order = OrderFactory.createOrder(cart);
		orderRepository.save(order);
	}

	public void addCustomer(String customerNumber, String orderNumber){
		Order order = getOrder(orderNumber);
		if (order == null)
			return;
		Customer customer = customerFeignClient.createOrder(customerNumber);
		if (customer !=null) {
			order.setCustomer(customer);
			orderRepository.save(order);
		}
	}

	@FeignClient("CustomerService")
	interface CustomerFeignClient{
		@GetMapping(value="/customer/{customerNumber}")
		public Customer createOrder(@PathVariable String customerNumber);
	}

}

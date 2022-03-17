package com.project.ShoppingCartCommandService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ShoppingCartCommandService.domain.Product;
import com.project.ShoppingCartCommandService.domain.ShoppingCart;
import com.project.ShoppingCartCommandService.repository.ShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ShoppingService {

	public static ObjectMapper objectMapper;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

//	@Autowired
//	private KafkaTemplate<String, Long> kafkaTemplate2;

	@Autowired
	private final ShoppingCartRepository shoppingCartRepository;
	private final OrderFeignClient orderFeignClient;
	private final ProductFeignClient productFeignClient;

	public ShoppingService(ShoppingCartRepository shoppingCartRepository, OrderFeignClient orderFeignClient,
			ProductFeignClient productFeignClient) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.orderFeignClient = orderFeignClient;
		this.productFeignClient = productFeignClient;
		this.objectMapper = new ObjectMapper();
	}

	public void addToShoppingCart(String cartId, String productNumber, int quantity) {
		Product product = productFeignClient.getProduct(productNumber);
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null && product.getStock().getQuantity() >= quantity) {
			ShoppingCart cart = cartOptional.get();
			cart.addToCart(product, quantity);
			shoppingCartRepository.save(cart);
		} else if (!cartOptional.isPresent() && product != null) {
			ShoppingCart cart = new ShoppingCart(cartId);
			cart.addToCart(product, quantity);
			shoppingCartRepository.save(cart);
		}

	}

	public void removeFromShoppingCart(String cartId, String productNumber) {
		Product product = productFeignClient.getProduct(productNumber);
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null) {
			ShoppingCart cart = cartOptional.get();
			cart.removeFromCart(product);
			shoppingCartRepository.save(cart);
		}
	}

	public void changeQuantity(String cartId, String productNumber, int quantity) {
		Product product = productFeignClient.getProduct(productNumber);
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null) {
			ShoppingCart cart = cartOptional.get();
			cart.changeFromCart(product, quantity);
			shoppingCartRepository.save(cart);
		}
	}

	public void sendCheckOutMessage(String cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		System.out.println(cart);
		String productOrderString = null;
		try {

			productOrderString = objectMapper.writeValueAsString(cart.get());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kafkaTemplate.send("CHECKOUT-FOR-ORDER", productOrderString);

	}

	@FeignClient("ProductCatalogService")
	interface ProductFeignClient {
		@GetMapping(value = "/product/{productNumber}")
		public Product getProduct(@PathVariable String productNumber);
	}

	@FeignClient("OrderService")
	interface OrderFeignClient {
		@PostMapping(value = "/order/create")
		public void createOrder(@RequestBody ShoppingCart cart);
	}
}

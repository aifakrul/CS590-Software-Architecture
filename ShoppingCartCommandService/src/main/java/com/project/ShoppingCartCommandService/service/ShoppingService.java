package com.project.ShoppingCartCommandService.service;


import com.project.ShoppingCartCommandService.domain.Product;
import com.project.ShoppingCartCommandService.domain.ShoppingCart;
import com.project.ShoppingCartCommandService.repository.ShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ShoppingService {

	@Autowired
	private final ShoppingCartRepository shoppingCartRepository;
	private final OrderFeignClient orderFeignClient;
	private final ProductFeignClient productFeignClient;

	public ShoppingService(ShoppingCartRepository shoppingCartRepository,
						   OrderFeignClient orderFeignClient, ProductFeignClient productFeignClient) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.orderFeignClient = orderFeignClient;
		this.productFeignClient = productFeignClient;
	}

	public void addToShoppingCart(String cartId, String productNumber, int quantity) {
		Product product = productFeignClient.getProduct(productNumber);
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null && product.getStock().getQuantity() >= quantity) {
			ShoppingCart cart = cartOptional.get();
			cart.addToCart(product, quantity);
			shoppingCartRepository.save(cart);
		}
		else if (!cartOptional.isPresent() && product!=null) {
			ShoppingCart cart = new ShoppingCart(cartId);
			cart.addToCart(product, quantity);
			shoppingCartRepository.save(cart);
		}

	}



	public void removeFromShoppingCart(String cartId, String productNumber){
		Product product = productFeignClient.getProduct(productNumber);
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null ) {
			ShoppingCart cart = cartOptional.get();
			cart.removeFromCart(product);
			shoppingCartRepository.save(cart);
		}
	}

	public void changeQuantity(String cartId, String productNumber, int quantity){
		Product product = productFeignClient.getProduct(productNumber);
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null ) {
			ShoppingCart cart = cartOptional.get();
			cart.changeFromCart(product, quantity);
			shoppingCartRepository.save(cart);
		}
	}



	public void checkout(String cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		if (cart.isPresent()) {
			orderFeignClient.createOrder(cart.get());
		}
	}

	@FeignClient("ProductCatalogService")
	interface ProductFeignClient{
		@GetMapping(value="/product/{productNumber}")
		public Product getProduct(@PathVariable String productNumber);
	}
	@FeignClient("OrderService")
	interface OrderFeignClient{
		@PostMapping(value="/order/create")
		public void createOrder(@RequestBody ShoppingCart cart);
	}
}

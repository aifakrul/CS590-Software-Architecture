package com.project.ShoppingCartQueryService.service;


import com.project.ShoppingCartQueryService.domain.Product;
import com.project.ShoppingCartQueryService.domain.ShoppingCart;
import com.project.ShoppingCartQueryService.repository.ShoppingCartRepository;
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

	@FeignClient("ProductCatalogService")
	interface ProductFeignClient{
		@GetMapping(value="/product/{productNumber}")
		public Product getProduct(@PathVariable String productNumber);
	}


	public ShoppingCart getShoppingCart(String cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		if (cart.isPresent())
		  return cart.get();
		else
			return null;
	}

	@FeignClient("OrderService")
	interface OrderFeignClient{
		@PostMapping(value="/order/create")
		public void createOrder(@RequestBody ShoppingCart cart);
	}
}

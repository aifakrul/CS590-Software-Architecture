package com.project.ShoppingCartCommandService.web;


import com.project.ShoppingCartCommandService.domain.CartHolder;
import com.project.ShoppingCartCommandService.domain.ShoppingCart;
import com.project.ShoppingCartCommandService.service.ShoppingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShoppingController {

	private final ShoppingService shoppingService;

	@Autowired
	public ShoppingController(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

	@PostMapping(value = "/cart/add")
	public ResponseEntity<?> addToCart(@RequestBody CartHolder holder) {
		shoppingService.addToShoppingCart(holder.getCartId(), holder.getProductNumber(), holder.getQuantity());
		return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
	}



	@PostMapping(value = "/cart/remove")
	public ResponseEntity<?> removeFromCart(@RequestBody CartHolder holder) {

		shoppingService.removeFromShoppingCart(holder.getCartId(), holder.getProductNumber());
		return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
	}

	@PostMapping(value = "/cart/change")
	public ResponseEntity<?> changeQuantity(@RequestBody CartHolder holder) {
		shoppingService.changeQuantity(holder.getCartId(), holder.getProductNumber(), holder.getQuantity());
		return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
	}

	@PostMapping(value = "/cart/checkout")
	public ResponseEntity<?> checkoutCart(@RequestBody CartHolder cartHolder) {
		System.out.println(cartHolder.getCartId());
		shoppingService.checkout(cartHolder.getCartId());
		return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
	}

}

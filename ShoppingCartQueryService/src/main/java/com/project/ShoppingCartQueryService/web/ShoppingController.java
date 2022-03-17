package com.project.ShoppingCartQueryService.web;


import com.project.ShoppingCartQueryService.CustomErrorType;
import com.project.ShoppingCartQueryService.domain.ShoppingCart;
import com.project.ShoppingCartQueryService.service.ShoppingService;
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

	@GetMapping("/cart/{cartId}")
	public ResponseEntity<?> getCart(@PathVariable String cartId) {
		ShoppingCart cart = shoppingService.getShoppingCart(cartId);
		if (cart == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Cart not found= "
					+ cartId + " is not available"),HttpStatus.NOT_FOUND);
		}
		cart.print();
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
	}




}

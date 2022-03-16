package com.project.OrderService.domain;

import java.util.Date;



public class OrderFactory {
	
	public static Order createOrder(ShoppingCart cart) {
		Order order = new Order(cart.getCartID(), new Date(),"placed");
		for (CartLine cline : cart.getCartLines()) {
			OrderLine oline = new OrderLine();
			//create an order product from a shopping product
			Product product = new Product(cline.getProduct().getProductNumber(), cline.getProduct().getDescription(),
					cline.getProduct().getPrice());
			oline.setProduct(product);
			oline.setQuantity(cline.getQuantity());
			order.addOrderLine(oline);
		}
		return order;
	}
}

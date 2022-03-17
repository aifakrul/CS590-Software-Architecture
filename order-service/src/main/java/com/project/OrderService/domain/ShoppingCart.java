package com.project.OrderService.domain;

import java.util.ArrayList;

public class ShoppingCart {
	private String cartID;
	private ArrayList<CartLine> cartLines;

	public ShoppingCart() {
	}

	public ShoppingCart(String cartID) {
		this.cartID = cartID;
		cartLines = new ArrayList<CartLine>();
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	public ArrayList<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(ArrayList<CartLine> cartLines) {
		this.cartLines = cartLines;
	}

	@Override
	public String toString() {
		return "ShoppingCart{" + "cartID='" + cartID + '\'' + ", cartLines=" + cartLines + '}';
	}
}

package com.project.ShoppingCartCommandService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;

@Document(collection = "cart")
public class ShoppingCart {
	@Id
	@JsonProperty
	private String cartID;
	@JsonProperty
	private ArrayList<CartLine> cartLines;

	public ShoppingCart() {
	}

	public ShoppingCart(String cartID) {
		this.cartID = cartID;
		cartLines = new ArrayList<CartLine>();
	}

	public void addToCart(Product product, int quantity) {
		for (CartLine cartLine : cartLines) {
			if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
				cartLine.setQuantity(cartLine.getQuantity() + quantity);
				return;
			}
		}
		CartLine cline = new CartLine();
		cline.setProduct(product);
		cline.setQuantity(quantity);
		cartLines.add(cline);
	}

	public void print() {
		System.out.println("Content of the shopping cart:");
		for (CartLine cartLine : cartLines) {
			System.out.println(cartLine.getQuantity() + " " + cartLine.getProduct().getProductNumber() + " "
					+ cartLine.getProduct().getDescription() + " " + cartLine.getProduct().getPrice());
		}
		System.out.println("Total price =" + calculatedTotalPrice());
	}

	public double calculatedTotalPrice() {
		double totalPrice = 0.0;
		for (CartLine cline : cartLines) {
			totalPrice = totalPrice + (cline.getProduct().getPrice() * cline.getQuantity());
		}
		return totalPrice;
	}

	public void removeFromCart(Product product) {
		Iterator<CartLine> iter = cartLines.iterator();
		while (iter.hasNext()) {
			CartLine cline = iter.next();
			if (cline.getProduct().getProductNumber().equals(product.getProductNumber())) {
				iter.remove();
			}
		}
	}

	public void changeFromCart(Product product, int quantity) {
		Iterator<CartLine> iter = cartLines.iterator();
		while (iter.hasNext()) {
			CartLine cline = iter.next();
			if (cline.getProduct().getProductNumber().equals(product.getProductNumber())) {
				cline.setQuantity(quantity);
				return;
			}
		}
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

package com.restapplication.domain.cart;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Stock {
	int quantity;

	public Stock(int quantity) {
		this.quantity = quantity;

	}

	public Stock() {
		super();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"quantity=" + quantity +
				'}';
	}
}

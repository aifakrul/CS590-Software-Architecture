package com.project.OrderService.domain;


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
		return "Stock{" + "quantity=" + quantity + '}';
	}
}

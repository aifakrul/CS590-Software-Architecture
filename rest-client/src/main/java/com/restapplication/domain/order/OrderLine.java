package com.restapplication.domain.order;

public class OrderLine {
	int quantity;
	Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderLine{" +
				"quantity=" + quantity +
				", product=" + product +
				'}';
	}
}

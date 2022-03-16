package com.restapplication.domain.product;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Stock {
	int quantity;
	String locationCode;

	public Stock(int quantity, String locationCode) {
		this.quantity = quantity;
		this.locationCode = locationCode;
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

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"quantity=" + quantity +
				", locationCode='" + locationCode + '\'' +
				'}';
	}
}

package com.restapplication.domain.order;

import com.restapplication.domain.cart.Stock;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="product")
public class Product {
	@Id
	String productNumber;
	double price;
	String description;

	public Product() {
		super();
	}

	public Product(String productNumber, String description, double price) {
		this.productNumber = productNumber;
		this.price = price;
		this.description = description;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productNumber='" + this.productNumber + '\'' +
				", price=" + this.getPrice()+
				", description='" + this.getDescription() + '\'' +
				'}';
	}
}

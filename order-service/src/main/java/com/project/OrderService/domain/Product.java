package com.project.OrderService.domain;



public class Product {

	String productNumber;
	double price;
	String description;
	Stock stock;

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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product{" + "productNumber='" + this.productNumber + '\'' + ", price=" + this.getPrice()
				+ ", description='" + this.getDescription() + '\'' + ", stock=" + this.getStock() + '}';
	}
}

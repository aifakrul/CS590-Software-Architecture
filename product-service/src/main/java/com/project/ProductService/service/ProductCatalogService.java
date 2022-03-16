package com.project.ProductService.service;

import com.project.ProductService.domain.Product;
import com.project.ProductService.domain.Stock;
import com.project.ProductService.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductCatalogService {

	ProductRepository productRepository;

	@Autowired
	public ProductCatalogService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void addProduct(String productNumber, String description, double price) {
		Product product = new Product(productNumber, description,  price);
		productRepository.save(product);
		
	}
	public Product getProduct(String productNumber) {
		Optional<Product> result = productRepository.findById(productNumber);
		if (result.isPresent())
		  return result.get();
		else
			return null;
	}
	public void setStock(String productNumber, int quantity, String locationCode) {
		Optional<Product> result = productRepository.findById(productNumber);
		if (result.isPresent()) {
			Product product = result.get();
			Stock stock = new Stock(quantity, locationCode);
			product.setStock(stock);
			productRepository.save(product);
		}		
	}

	public void updateProduct(String productNumber, double price, String description){
		Optional<Product> result = productRepository.findById(productNumber);
		if (result.isPresent()) {
			Product product = result.get();
			product.setPrice(price);
			product.setDescription(description);
			productRepository.save(product);
		}

	}
}

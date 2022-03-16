package com.project.ProductService.web;

import com.project.ProductService.CustomErrorType;
import com.project.ProductService.domain.Product;
import com.project.ProductService.domain.StockHolder;
import com.project.ProductService.service.ProductCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

	ProductCatalogService productCatalogService;

	@Autowired
	public ProductController(ProductCatalogService productCatalogService) {
		this.productCatalogService = productCatalogService;
	}

	@PostMapping(value = "/product")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		productCatalogService.addProduct(product.getProductNumber(), product.getDescription(), product.getPrice());
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	@GetMapping("/product/{productNumber}")
	public ResponseEntity<?> getProduct(@PathVariable String productNumber) {
		Product product = productCatalogService.getProduct(productNumber);
		if (product == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product not found= "
					+ productNumber + " is not available"),HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping(value = "/stock")
	public ResponseEntity<?> setStock(@RequestBody StockHolder holder){
		productCatalogService.setStock(holder.getProductNumber(), holder.getQuantity(), holder.getLocationCode());
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
}

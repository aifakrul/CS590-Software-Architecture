package com.project.ShoppingCartCommandService.repository;

import com.project.ShoppingCartCommandService.domain.ShoppingCart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

}

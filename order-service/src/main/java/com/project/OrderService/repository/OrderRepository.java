package com.project.OrderService.repository;


import com.project.OrderService.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;




public interface OrderRepository extends MongoRepository<Order, String> {

}

package com.project.CustomerService.repository;


import com.project.CustomerService.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;




public interface CustomerRepository extends MongoRepository<Customer, String> {

}

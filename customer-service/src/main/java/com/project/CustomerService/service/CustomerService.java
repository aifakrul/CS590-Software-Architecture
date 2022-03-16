package com.project.CustomerService.service;


import java.util.Optional;

import com.project.CustomerService.domain.Customer;
import com.project.CustomerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public Customer getCustomer(String customerNumber) {
	  Optional<Customer> customerOptional = customerRepository.findById(customerNumber);
	  if (customerOptional.isPresent()) {
		  Customer customer = customerOptional.get();
		  return customer;
	  }
	  else
		  return null;
  }


}

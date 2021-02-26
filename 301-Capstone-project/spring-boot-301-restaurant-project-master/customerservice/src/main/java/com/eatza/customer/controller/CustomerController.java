package com.eatza.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.customer.dto.CustomerDto;
import com.eatza.customer.exception.CustomerNotFoundException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/savecustomer")
	public Customer saveCustomer(@RequestBody Customer customer) {

		Customer responseCustomer = customerService.saveCustomer(customer);
		return responseCustomer;

	}

	@GetMapping("/getallcustomers")
	public List<Customer> getAllCustomers() {

		return customerService.getAllCustomers();

	}

	@GetMapping("/getcustomerbyid/{id}")
	public Customer getCustomerById(@PathVariable("id") Long customerId) {
		return customerService.getCustomerById(customerId);

	}

	@GetMapping("/deletecustomerbyid/{customerId}")
	public String deleteCustomerById(@PathVariable Long customerId) {
		customerService.deleteCustomerById(customerId);
		return "Successfully deleted";
	}

	@PutMapping("/updatecustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {

		try {
			String res = customerService.updateCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} catch (CustomerNotFoundException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}

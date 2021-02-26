package com.eatza.customer.service;

import java.util.List;

import com.eatza.customer.exception.CustomerNotFoundException;
import com.eatza.customer.model.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(Long customerId);
	public String updateCustomer(Customer customer) throws CustomerNotFoundException;
	public String deleteCustomerById(Long customerId);
	
}

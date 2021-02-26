package com.eatza.customer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.customer.exception.CustomerNotFoundException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;
	

	@Override
	public Customer saveCustomer(Customer customer) {
		logger.debug("In save customer method, calling repo");
		return customerRepository.save(customer);

	}

	@Override
	public List<Customer> getAllCustomers() {
		logger.debug("In get all customer method, calling repo");
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		logger.debug("In get customer by id, calling repo");
		return customerRepository.findById(customerId).get();
	}

	@Override
	public String deleteCustomerById(Long customerId) {
		logger.debug("In delete customer method, calling repo");
		customerRepository.deleteById(customerId);
		return "Deleted Successfully";

	}

	@Override
	public String updateCustomer(Customer customer) throws CustomerNotFoundException {

		logger.debug("In update customer method, calling repo");
		Optional<Customer> res = customerRepository.findById(customer.getCustomerId());
		Customer resCustomer = null;
		if (res.isPresent()) {
			resCustomer = res.get();
			resCustomer.setAge(customer.getAge());
			resCustomer.setCustomerName(customer.getCustomerName());
			customerRepository.save(resCustomer);
			return "Success";
		}

		else {
			throw new CustomerNotFoundException("Invalid customer id");
		}

		

	}

}

package com.eatza.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.customer.exception.CustomerNotFoundException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.repository.CustomerRepository;
import com.eatza.customer.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureJson
@AutoConfigureJsonTesters
class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Test
	public void saveCustomerTest() {

		Customer customer = getCustomer();
		Long customerId = 1L;

		when(customerRepository.save(customer)).thenReturn(customer);
		Customer returnedCustomer = customerServiceImpl.saveCustomer(customer);

		assertNotNull(customerServiceImpl.saveCustomer(customer));
		assertEquals(customer, returnedCustomer);

		assertEquals("Chai", returnedCustomer.getCustomerName());
		assertEquals(24, returnedCustomer.getAge());
		assertEquals(customerId, returnedCustomer.getCustomerId());

	}

	@Test
	public void getAllCustomersTest() {
		List<Customer> customers = getCustomerList();

		when(customerRepository.findAll()).thenReturn(customers);
		assertNotNull(customerServiceImpl.getAllCustomers());
		assertEquals(24, customerServiceImpl.getAllCustomers().get(0).getAge());
		assertEquals("Chai", customerServiceImpl.getAllCustomers().get(0).getCustomerName());

		assertEquals(23, customerServiceImpl.getAllCustomers().get(1).getAge());
		assertEquals("Tom", customerServiceImpl.getAllCustomers().get(1).getCustomerName());

		assertEquals(25, customerServiceImpl.getAllCustomers().get(2).getAge());
		assertEquals("Jerry", customerServiceImpl.getAllCustomers().get(2).getCustomerName());

		assertEquals(27, customerServiceImpl.getAllCustomers().get(3).getAge());
		assertEquals("Cris", customerServiceImpl.getAllCustomers().get(3).getCustomerName());

	}

	@Test
	public void getCutomerByIdTest() {
		Long custId = 1L;
		Customer customer = getCustomer();
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(1L)).thenReturn(checkCustomer);
		assertNotNull(customerServiceImpl.getCustomerById(1L));
		assertEquals(custId, customerServiceImpl.getCustomerById(1L).getCustomerId());
		assertEquals("Chai", customerServiceImpl.getCustomerById(1L).getCustomerName());
		assertEquals(24, customerServiceImpl.getCustomerById(1L).getAge());
	}

	@Test
	public void getCutomerByIdTest1() {
		Long custId = 1L;
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setAge(24);
		customer.setCustomerName("Chai");
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(1L)).thenReturn(checkCustomer);
		assertNotNull(customerServiceImpl.getCustomerById(1L));
		assertEquals(custId, customerServiceImpl.getCustomerById(1L).getCustomerId());
		assertEquals("Chai", customerServiceImpl.getCustomerById(1L).getCustomerName());
		assertEquals(24, customerServiceImpl.getCustomerById(1L).getAge());
	}

	@Test
	public void deleteCustomerByIdTest1() {
		Long customerId = 1L;
		customerRepository.deleteById(customerId);
		assertEquals("Deleted Successfully", customerServiceImpl.deleteCustomerById(customerId));

	}

	

	@Test
	public void updateCustomerIfPresent() throws CustomerNotFoundException {

		Long customerId = 1L;
		Customer customer = getCustomer();
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(checkCustomer);
		assertEquals("Success", customerServiceImpl.updateCustomer(customer));

	}

	@Test
	public void updateCustomerNameIfPresent() throws CustomerNotFoundException {

		Long customerId = 1L;
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("Chai");
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(checkCustomer);
		assertEquals("Success", customerServiceImpl.updateCustomer(customer));

	}

	@Test
	public void updateCustomerAgeIfPresent() throws CustomerNotFoundException {

		Long customerId = 1L;
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setAge(24);
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(checkCustomer);
		assertEquals("Success", customerServiceImpl.updateCustomer(customer));

	}

	@Test()
	public void updateCustomerIfNotPresent() throws CustomerNotFoundException {

		Long customerId = 7L;
		Customer customer = getCustomer();
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(checkCustomer);

		CustomerNotFoundException thrown = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.updateCustomer(customer));
		assertTrue(thrown.getMessage().contains("Invalid customer id"));

	}

	@Test()
	public void updateCustomerIfNotPresent1() throws CustomerNotFoundException {

		Long customerId = 7L;
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setAge(24);
		customer.setCustomerName("Chai");
		Optional<Customer> checkCustomer = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(checkCustomer);

		CustomerNotFoundException thrown = assertThrows(CustomerNotFoundException.class,
				() -> customerServiceImpl.updateCustomer(customer));
		assertTrue(thrown.getMessage().contains("Invalid customer id"));

	}

	public List<Customer> getCustomerList() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Chai", 24));
		customers.add(new Customer(2L, "Tom", 23));
		customers.add(new Customer(3L, "Jerry", 25));
		customers.add(new Customer(4L, "Cris", 27));

		return customers;
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setAge(24);
		customer.setCustomerName("Chai");
		return customer;
	}

}

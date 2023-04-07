package com.assignment.CustomerService.repository;

import com.assignment.CustomerService.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRepositoryTest {

    private static final String TEST_FIRST_NAME = "Nada";
    private static final String TEST_LAST_NAME = "OUJI";

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindCustomerById() {
        // Given
        Customer customer = new Customer(TEST_FIRST_NAME, TEST_LAST_NAME);
        customerRepository.save(customer);

        // When
        Optional<Customer> result = customerRepository.findById(customer.getId());

        // Then
        assertTrue(result.isPresent());
    }

    @Test
    public void shouldSaveCustomer() {
        // Given
        Customer customer = new Customer(TEST_FIRST_NAME, TEST_LAST_NAME);

        // When
        Customer savedCustomer = customerRepository.save(customer);

        // Then
        assertNotNull(savedCustomer.getId());
        assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
        assertEquals(customer.getLastName(), savedCustomer.getLastName());
    }

    @Test
    public void shouldDeleteCustomer() {
        // Given
        Customer customer = new Customer(TEST_FIRST_NAME, TEST_LAST_NAME);
        customerRepository.save(customer);

        // When
        customerRepository.delete(customer);

        // Then
        assertFalse(customerRepository.existsById(customer.getId()));
    }

}

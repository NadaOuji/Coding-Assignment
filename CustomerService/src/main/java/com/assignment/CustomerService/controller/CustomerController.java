package com.assignment.CustomerService.controller;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.exception.ResourceNotFoundException;
import com.assignment.CustomerService.mapper.CustomerMapper;
import com.assignment.CustomerService.model.Customer;
import com.assignment.CustomerService.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CustomerController is responsible for handling RESTful endpoints related
 * to customer operations
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    public CustomerController(CustomerService customerService,CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;

    }

    /**
     * Creates a new customer.
     *
     * @param customerDTO The customer data in the form of an CustomerDTO object.
     * @return An CustomerResponseDTO object containing the newly created customer data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        logger.info("Creating a new customer with data {}", customerDTO);
        Customer customer = customerService.createCustomer(customerDTO);
        CustomerResponseDTO customerResponseDTO = customerMapper.toDTO(customer);
        logger.info("Created a new customer with data {}", customerResponseDTO);
        return customerResponseDTO;
    }

    /**
     * Retrieves the customer with the given ID.
     *
     * @param id the ID of the customer to retrieve
     * @return An CustomerResponseDTO object containing the customer data for the specified customer.
     * @throws ResourceNotFoundException if the specified customer is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        logger.info("Retrieving customer with id {}", id);
        Optional<CustomerResponseDTO> customerOpt = Optional.ofNullable(customerService.getCustomerById(id));
        CustomerResponseDTO customer = customerOpt.orElseThrow(() -> {
            logger.error("Could not find customer with id {}", id);
            return new ResourceNotFoundException("Customer not found with id " + id);
        });
        logger.info("Retrieved customer with data {}", customer);
        return ResponseEntity.ok(customer);


    }

    /**
     * Retrieves a list of all customers.
     *
     * @return A List of CustomerResponseDTO objects containing the customer data for all customers.
     */
    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        logger.info("Retrieving all customers");
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        logger.info("Retrieved {} customers", customers.size());
        return customers;
    }

}

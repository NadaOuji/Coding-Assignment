package com.assignment.CustomerService.controller;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.exception.ResourceNotFoundException;
import com.assignment.CustomerService.mapper.CustomerMapper;
import com.assignment.CustomerService.model.Customer;
import com.assignment.CustomerService.service.CustomerService;
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
        Customer customer = customerService.createCustomer(customerDTO);
        return customerMapper.toDTO(customer);
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
        Optional<CustomerResponseDTO> customerOpt = Optional.ofNullable(customerService.getCustomerById(id));
        CustomerResponseDTO customer = customerOpt.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return ResponseEntity.ok(customer);

    }

    /**
     * Retrieves a list of all customers.
     *
     * @return A List of CustomerResponseDTO objects containing the customer data for all customers.
     */
    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}

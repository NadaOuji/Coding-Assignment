package com.assignment.CustomerService.service;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerDTO customerDTO);

    CustomerResponseDTO getCustomerById(Long customerId);

    List<CustomerResponseDTO> getAllCustomers();

}

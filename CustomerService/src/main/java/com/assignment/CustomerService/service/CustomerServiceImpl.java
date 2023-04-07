package com.assignment.CustomerService.service;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.mapper.CustomerMapper;
import com.assignment.CustomerService.model.Customer;
import com.assignment.CustomerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toCustomersDTO(customers);
    }
}

package com.assignment.CustomerService.service;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.mapper.CustomerMapper;
import com.assignment.CustomerService.model.Customer;
import com.assignment.CustomerService.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerServiceImpl customerService;

    private CustomerDTO customerDTO;
    private Customer customer;
    private CustomerResponseDTO customerResponseDTO;

    @BeforeEach
    public void setup() {
        customerDTO = new CustomerDTO(1L,"Nada", "OUJI");
        customer = new Customer(1L, "Nada", "OUJI");
        customerResponseDTO = new CustomerResponseDTO(1L, "Nada", "OUJI");

        doReturn(customer).when(customerRepository).save(any(Customer.class));
        doReturn(Optional.of(customer)).when(customerRepository).findById(anyLong());
        doReturn(List.of(customer)).when(customerRepository).findAll();
        doReturn(customer).when(customerMapper).toEntity(customerDTO);
        doReturn(customerResponseDTO).when(customerMapper).toDTO(customer);
        doReturn(List.of(customerResponseDTO)).when(customerMapper).toCustomersDTO(List.of(customer));
    }

    @Test
    public void testCreateCustomer_shouldSaveCustomerToRepository() {
        // When
        Customer result = customerService.createCustomer(customerDTO);

        // Then
        assertNotNull(result.getId());
        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals(customer.getLastName(), result.getLastName());
        verify(customerMapper).toEntity(customerDTO);
        verify(customerRepository).save(customer);
    }

    @Test
    public void testGetCustomerById_shouldReturnCustomerResponseDTO() {
        // When
        CustomerResponseDTO result = customerService.getCustomerById(customer.getId());

        // Then
        assertEquals(customerResponseDTO, result);
        verify(customerRepository).findById(customer.getId());
        verify(customerMapper).toDTO(customer);
    }
    @Test
    public void testGetAllCustomers_shouldReturnListOfCustomerResponseDTOs() {
        // When
        List<CustomerResponseDTO> result = customerService.getAllCustomers();

        // Then
        assertEquals(List.of(customerResponseDTO), result);
        verify(customerRepository).findAll();
        verify(customerMapper).toCustomersDTO(List.of(customer));
    }
}

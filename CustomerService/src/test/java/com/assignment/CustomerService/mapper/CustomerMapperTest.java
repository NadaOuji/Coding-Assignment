package com.assignment.CustomerService.mapper;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void toEntity_shouldMapCorrectly() {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);

        // When
        Customer customer = customerMapper.toEntity(customerDTO);

        // Then
        assertEquals(customerDTO.getId(), customer.getId());
    }

    @Test
    public void toDTO_shouldMapCorrectly() {
        // Given
        Customer customer = new Customer();
        customer.setId(1L);

        // When
        CustomerResponseDTO customerResponseDTO = customerMapper.toDTO(customer);

        // Then
        assertEquals(customer.getId(), customerResponseDTO.getId());
    }

    @Test
    public void toCustomersDTO_shouldMapCorrectly() {
        // Given
        Customer customer1 = new Customer();
        customer1.setId(1L);

        Customer customer2 = new Customer();
        customer2.setId(2L);

        List<Customer> customers = Arrays.asList(customer1, customer2);

        // When
        List<CustomerResponseDTO> customerResponseDTOs = customerMapper.toCustomersDTO(customers);

        // Then
        assertIterableEquals(customers.stream().map(Customer::getId).collect(Collectors.toList()),
                customerResponseDTOs.stream().map(CustomerResponseDTO::getId).collect(Collectors.toList()));
    }
}

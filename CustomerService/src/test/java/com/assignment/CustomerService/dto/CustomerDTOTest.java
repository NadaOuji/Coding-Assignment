package com.assignment.CustomerService.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerDTOTest {

    @Test
    public void testCustomerDTO() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";

        CustomerDTO customerDTO = new CustomerDTO(id, firstName, lastName);

        assertEquals(id, customerDTO.getId());
        assertEquals(firstName, customerDTO.getFirstName());
        assertEquals(lastName, customerDTO.getLastName());
    }
}

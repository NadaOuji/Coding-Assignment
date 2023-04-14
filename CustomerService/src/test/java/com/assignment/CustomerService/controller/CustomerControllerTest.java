package com.assignment.CustomerService.controller;

import com.assignment.CustomerService.dto.CustomerDTO;
import com.assignment.CustomerService.dto.CustomerResponseDTO;
import com.assignment.CustomerService.mapper.CustomerMapper;
import com.assignment.CustomerService.model.Customer;
import com.assignment.CustomerService.repository.CustomerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void testCreateCustomer() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("Nada");
        customerDTO.setLastName("OUJI");

        // When
        MvcResult result = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        CustomerResponseDTO createdCustomerResponseDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CustomerResponseDTO.class);
        Customer createdAccount = customerRepository.findById(createdCustomerResponseDTO.getId())
                .orElseThrow(() -> new AssertionError("Customer not created"));
        assertEquals(customerDTO.getId(), createdAccount.getId());
        assertEquals(customerDTO.getFirstName(), createdAccount.getFirstName());
        assertEquals(customerDTO.getLastName(), createdAccount.getLastName());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        // Given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Nada");
        customer.setLastName("OUJI");
        customerRepository.save(customer);

        // When
        MvcResult result = mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        CustomerResponseDTO customerResponseDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CustomerResponseDTO.class);
        assertEquals(customerMapper.toDTO(customer), customerResponseDTO);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        // Given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Nada");
        customer1.setLastName("OUJI");

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Nadia");
        customer2.setLastName("SANAA");

        customerRepository.save(customer2);
        // When
        MvcResult result = mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        List<CustomerResponseDTO> customerResponseDTOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(2, customerResponseDTOList.size());
        assertEquals(customerMapper.toDTO(customer1), customerResponseDTOList.get(0));
        assertEquals(customerMapper.toDTO(customer2), customerResponseDTOList.get(1));
    }
}

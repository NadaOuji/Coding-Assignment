package com.assignment.CustomerService.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CustomerResponseDTOTest {

    private CustomerResponseDTO customerResponseDTO;

    @BeforeEach
    void setUp() {
        customerResponseDTO = new CustomerResponseDTO(1L, "Nada", "OUJI");
    }

    @Test
    void testGetId() {
        assertEquals(1L, customerResponseDTO.getId());
    }

    @Test
    void testGetFirstName() {
        assertEquals("Nada", customerResponseDTO.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("OUJI", customerResponseDTO.getLastName());
    }

    @Test
    void testEqualsWithSameObject() {
        assertEquals(customerResponseDTO, customerResponseDTO);
    }

    @Test
    void testEqualsWithDifferentObject() {
        CustomerResponseDTO other = new CustomerResponseDTO(1L, "Nada", "OUJI");
        assertEquals(customerResponseDTO, other);
    }

    @ParameterizedTest
    @ValueSource(longs = {2L, 3L, 4L})
    @DisplayName("Test setId method with different input values")
    void testSetId(long id) {
        customerResponseDTO.setId(id);
        assertEquals(id, customerResponseDTO.getId());
    }

    @ParameterizedTest
    @CsvSource({
            "Nada, OUJI, true",
            "Nadia, Sanaa, false",
    })
    @DisplayName("Test equals method with different input values")
    void testEqualsWithDifferentInputValues(String firstName, String lastName, boolean expected) {
        CustomerResponseDTO other = new CustomerResponseDTO(1L, firstName, lastName);
        assertEquals(expected, customerResponseDTO.equals(other));
    }
}

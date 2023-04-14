package com.assignment.CustomerService.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Nada");
        customer.setLastName("OUJI");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, customer.getId());
        assertEquals("Nada", customer.getFirstName());
        assertEquals("OUJI", customer.getLastName());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Nada, OUJI, 2, Nada, OUJI, false",
            "1, Nada, OUJI, 1, Hana, OUJI, false",
            "1, Nada, OUJI, 1, Nadia, Sanaa, false"
    })
    public void testEqualsAndHashCode(Long id1, String firstName1, String lastName1,
                                      Long id2, String firstName2, String lastName2,
                                      boolean expectedEquality) {
        Customer customer1 = new Customer(id1, firstName1, lastName1);
        Customer customer2 = new Customer(id2, firstName2, lastName2);

        assertEquals(expectedEquality, customer1.equals(customer2));
        if (expectedEquality) {
            assertEquals(customer1.hashCode(), customer2.hashCode());
        } else {
            assertNotEquals(customer1.hashCode(), customer2.hashCode());
        }
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer("Nada", "OUJI");
        assertNull(customer.getId());
        assertEquals("Nada", customer.getFirstName());
        assertEquals("OUJI", customer.getLastName());
    }
}

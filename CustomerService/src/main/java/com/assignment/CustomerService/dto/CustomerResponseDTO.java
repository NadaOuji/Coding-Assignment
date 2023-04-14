package com.assignment.CustomerService.dto;

import java.util.Objects;

public class CustomerResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Long customerId, String firstName, String lastName) {
        this.id = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof CustomerResponseDTO other)) return false;
        return Objects.equals(id, other.id) &&
                Objects.equals(firstName, other.firstName) &&
                Objects.equals(lastName, other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}

package com.ust.customer_service.dto;

import com.ust.customer_service.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CustomerDto(
        long id,

        @NotEmpty(message = "Name is required")
        @Length(min = 1, max = 255, message = "Name length should be between 1-255")
        String name,

        @NotEmpty(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotEmpty(message = "Phone number is required")
        @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$")
        String phoneNumber
) {

        public Customer toCustomer(CustomerDto dto) {
                return new Customer(
                        dto.name,
                        dto.email,
                        dto.phoneNumber
                );
        }

        public CustomerDto toCustomerDto(Customer customer) {
                return new CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhoneNumber()
                );
        }
}

package com.ust.customer_service.controller;

import com.ust.customer_service.dto.CustomerDto;
import com.ust.customer_service.entity.Customer;
import com.ust.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto dto) {
        Customer customer = dto.toCustomer(dto);
        customer = customerService.createCustomer(customer);
        dto = dto.toCustomerDto(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id,@Valid @RequestBody CustomerDto dto) {
        Customer customer = dto.toCustomer(dto);
        customerService.updateCustomer(id, customer);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}

package com.ust.customer_service.service;

import com.ust.customer_service.entity.Customer;
import com.ust.customer_service.exception.CustomerNotFoundException;
import com.ust.customer_service.exception.DuplicateCustomerException;
import com.ust.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id: "+id+" not found"));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.findAll().stream().anyMatch(existingCustomer -> existingCustomer.getEmail().equals(customer.getEmail()))) {
            throw new DuplicateCustomerException("Customer with email: "+ customer.getEmail()+" already exists");
        }
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        if (customerRepository.findById(id).isPresent()){
            customer.setId(id);
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new CustomerNotFoundException("Customer with id: "+id+" not found");
        }
    }

    @Override
    public void deleteCustomer(long id) {
        if (customerRepository.findById(id).isPresent()){
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer with id: "+id+" not found");
        }
    }
}

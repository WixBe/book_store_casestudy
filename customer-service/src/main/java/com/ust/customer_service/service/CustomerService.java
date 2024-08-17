package com.ust.customer_service.service;

import com.ust.customer_service.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(long id,Customer customer);

    void deleteCustomer(long id);
}

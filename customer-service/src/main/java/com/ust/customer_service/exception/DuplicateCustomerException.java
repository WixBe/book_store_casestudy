package com.ust.customer_service.exception;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String s) {
        super(s);
    }
}

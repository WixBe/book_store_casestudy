package com.ust.order_service.dto;

public record Book (

    long id,
    String title,
    String author,
    long price,
    int stock
    )
{}
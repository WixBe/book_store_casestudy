package com.ust.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long bookId;

    private long customerId;

    private int quantity;

    private String status;

    public Order(long bookId, long customerId, int quantity, String status) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.status = status;
    }
};

package com.ust.book_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;
    private long price;
    private int stock;

    public Book(String title, String author, long price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }
}

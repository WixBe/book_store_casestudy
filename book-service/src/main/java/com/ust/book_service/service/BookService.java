package com.ust.book_service.service;

import com.ust.book_service.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(long id);

    Book createBook(Book book);

    Book updateBook(long id,Book book);

    void deleteBookById(long id);
}

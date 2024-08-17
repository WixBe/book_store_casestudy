package com.ust.book_service.service;

import com.ust.book_service.entity.Book;
import com.ust.book_service.exceptions.BookNotFoundException;
import com.ust.book_service.exceptions.DuplicateBookException;
import com.ust.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() ->new BookNotFoundException("Book with id: "+id+" not found"));
    }

    @Override
    public Book createBook(Book book) {
        if (bookRepository.findAll().stream().anyMatch(existingBook -> existingBook.getAuthor().equals(book.getAuthor()) && existingBook.getTitle().equals(book.getTitle()))) {
            throw new DuplicateBookException("Book already exists");
        }
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book updateBook(long id,Book book) {
        if (bookRepository.findById(id).isPresent()) {
            book.setId(id);
            return bookRepository.saveAndFlush(book);
        } else {
            throw new BookNotFoundException("Book with id: "+id+"not found");
        }
    }

    @Override
    public void deleteBookById(long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("Book with id: "+id+"not found");
        }
    }
}

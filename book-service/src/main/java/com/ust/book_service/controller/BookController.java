package com.ust.book_service.controller;

import com.ust.book_service.dto.BookDto;
import com.ust.book_service.entity.Book;
import com.ust.book_service.service.BookService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto) {
        Book book = dto.toBook(dto);
        book = bookService.createBook(book);
        dto = dto.toBookDto(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@Valid @PathVariable long id,@RequestBody BookDto dto) {
        Book book = dto.toBook(dto);
        bookService.updateBook(id, book);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.ust.book_service.dto;

import com.ust.book_service.entity.Book;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record BookDto(

        long id,

        @NotEmpty(message = "Title is required")
        @Length(min = 1, max = 255, message = "Title length should be between 1-255")
        String title,

        @NotEmpty(message = "Author is required")
        @Length(min = 1, max = 255, message = "Title length should be between 1-255")
        String author,

        @NotNull(message = "Price can't be empty")
        @Positive(message = "Price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Price must have up to 2 decimal places.")
        long price,

        @NotNull(message = "Stock is required")
        @PositiveOrZero(message = "Stock must be 0 or more")
        int stock

) {

        public Book toBook(BookDto dto) {
                return new Book(
                        dto.title,
                        dto.author,
                        dto.price,
                        dto.stock
                );
        }

        public BookDto toBookDto(Book book) {
                return new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPrice(),
                        book.getStock()
                );
        }
}

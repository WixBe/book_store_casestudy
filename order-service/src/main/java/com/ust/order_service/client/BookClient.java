package com.ust.order_service.client;

import com.ust.order_service.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service")
public interface BookClient {

    @GetMapping("/books/{id}/stock")
    int getStockFromBookId(@PathVariable long id);

    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable long id);

    @PutMapping("/books/{id}")
    Book updateBook(@PathVariable long id, @RequestBody Book book);
}

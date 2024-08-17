package com.ust.book_service.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String s){
        super(s);
    }
}

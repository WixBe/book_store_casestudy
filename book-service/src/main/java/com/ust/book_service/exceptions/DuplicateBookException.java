package com.ust.book_service.exceptions;

public class DuplicateBookException extends RuntimeException{
    public DuplicateBookException(String s){
        super(s);
    }
}

package com.equifax.library.service;

import com.equifax.library.model.Book;

public interface BookService {
//public Book addBook(Book book);
//public void deleteBook(int id);
public Book addBook(Book book);
public String deleteBook(int bookid, int verifyuser);
}

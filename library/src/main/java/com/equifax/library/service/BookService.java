package com.equifax.library.service;

import java.util.List;

import com.equifax.library.model.Book;

public interface BookService {
public Book addBook(Book book);
public void deleteBook(int id);
public List<Book> getAllBooks();
public String updateBookStatus(Integer bookId,Integer userId);
}

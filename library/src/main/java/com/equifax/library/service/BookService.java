package com.equifax.library.service;

import java.util.Optional;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;

public interface BookService {
public Book addBook(BookDTO bookDTO);
public String deleteBook(int id);
public String validateBook(BookDTO bookDTO);
public Book getBookId(int bookId);

}

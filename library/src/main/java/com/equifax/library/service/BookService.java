package com.equifax.library.service;

import java.util.List;


import com.equifax.library.model.Book;
import com.equifax.library.dto.BookDTO;

public interface BookService {
public Book addBook(BookDTO bookDTO);
public String deleteBook(int id);
public String validateBook(BookDTO bookDTO);
public List<Book> getBookName(String bookName);
public List<Book> getAllBooks();
public String updateBookStatus(Integer bookId,Integer userId);


}

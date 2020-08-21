package com.equifax.library.service;

import java.util.ArrayList;
import java.util.Optional;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;



public interface BookService {
	
public ArrayList<BookDTO> getAllBooks();
public String updateBookStatus(Integer bookId,Integer userId);

public String deleteBook(int id);
public Book addBook(BookDTO bookDTO);
public String validateBook(BookDTO bookDTO);
public Optional<Book> getBookId(Integer bookId);

}

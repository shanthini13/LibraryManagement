package com.equifax.library.service;


import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import com.equifax.library.model.Book;
import com.equifax.library.dto.BookDTO;



public interface BookService {
	
public ArrayList<BookDTO> getAllBooks();
public String updateBookStatus(Integer bookId,Integer userId);
public String deleteBook(int id);
public Book addBook(BookDTO bookDTO);
public String validateBook(BookDTO bookDTO);
public Optional<Book> getBookbyId(Integer bookId);



}

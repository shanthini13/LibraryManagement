package com.equifax.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.equifax.library.model.Book;
import com.equifax.library.service.BookService;
import com.equifax.library.service.UserService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/addbook/{userid}", method = RequestMethod.POST)
	public Book addBook(@RequestBody Book book, @PathVariable int userid) {
		if(userService.authenticateUser(userid)) {
		return bookService.addBook(book);
		}
		else
		return book;
	}

	@RequestMapping(value = "/deletebook/{bookid}", method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable int bookid, @RequestHeader("Verifyuser") int Verifyuser) {
		if(userService.authenticateUser(Verifyuser)) {
		return bookService.deleteBook(bookid, Verifyuser);
		}
		else 
			return null;
	}
}
